package la_dominga.servidor;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.configuraciones.Resultado;
import la_dominga.entidades.Cliente;
import la_dominga.entidades.Usuario;
import la_dominga.entidades.dto.ActualizarUsuarioDTO;
import la_dominga.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import static la_dominga.configuraciones.Resultado.*;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public RespuestaServidor<Usuario> iniciarSesion(String correo, String clave) {
        Optional<Usuario> optU = repository.findByCorreo(correo);
        if (optU.isPresent() && passwordEncoder.matches(clave, optU.get().getClave())) {
            return new RespuestaServidor<>(TIPO_AUTH, RPTA_OK, "Haz iniciado sesión correctamente", optU.get());
        } else {
            return new RespuestaServidor<>(TIPO_AUTH, RPTA_WARNING, "Correo o contraseña incorrecta", null);
        }
    }

    public RespuestaServidor<Usuario> registrarUsuario(Usuario usuario) {
        // Verificar si ya existe un usuario con el mismo correo electrónico
        Optional<Usuario> usuarioExistente = repository.findByCorreo(usuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            // Usuario ya existe, no se puede crear uno nuevo con el mismo correo
            return new RespuestaServidor<>(TIPO_DATA, RPTA_WARNING, "Ya existe un usuario con este correo electrónico", null);
        }

        // Codificar la contraseña del nuevo usuario
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));

        // Guardar el nuevo usuario
        Usuario usuarioGuardado = repository.save(usuario);
        return new RespuestaServidor<>(TIPO_DATA, RPTA_OK, "Usuario registrado con éxito", usuarioGuardado);
    }



    public List<Usuario> listarUsuarios() {
        return repository.listarTodosLosUsuarios();
    }

    public RespuestaServidor<Usuario> actualizarUsuario(ActualizarUsuarioDTO actualizarUsuarioDTO) {
        int idUsuario = actualizarUsuarioDTO.getId();
        Optional<Usuario> usuarioOpt = repository.findById(idUsuario);

        if (!usuarioOpt.isPresent()) {
            return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_ERROR, Resultado.OPERACION_ERRONEA, null);
        }

        Usuario usuario = usuarioOpt.get();

        // Verificar si el correo ya está en uso por otro usuario
        Optional<Usuario> usuarioExistente = repository.findByCorreo(actualizarUsuarioDTO.getCorreo());
        if (usuarioExistente.isPresent() && usuarioExistente.get().getId() != idUsuario) {
            // Correo ya en uso por otro usuario
            return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_ERROR, "Correo ya en uso", null);
        }

        usuario.setCorreo(actualizarUsuarioDTO.getCorreo());

        // Actualiza los datos del cliente si se proporcionan en el DTO
        Cliente clienteDTO = actualizarUsuarioDTO.getCliente();
        if (clienteDTO != null) {
            Cliente cliente = usuario.getCliente();
            if (cliente == null) {
                cliente = new Cliente();
                usuario.setCliente(cliente);
            }
            cliente.setNombreCompleto(clienteDTO.getNombreCompleto());
            cliente.setNumeroTelefonico(clienteDTO.getNumeroTelefonico());
        }

        // Guarda los cambios
        repository.save(usuario);

        return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_OK, Resultado.OPERACION_CORRECTA, usuario);
    }


    public RespuestaServidor<List<Usuario>> buscarPorNombre(String nombreCompleto) {
        List<Usuario> usuarios = repository.findByNombreCompleto(nombreCompleto);
        if (usuarios.isEmpty()) {
            return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_WARNING, "No se encontraron usuarios con ese nombre", null);
        }
        return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_OK, "Usuarios encontrados", usuarios);
    }
    // Método para obtener un usuario por su ID
    public Optional<Usuario> obtenerUsuarioPorId(int usuarioId) {
        return repository.findById(usuarioId);
    }
}
