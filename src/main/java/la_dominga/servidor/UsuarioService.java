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

    public RespuestaServidor<Usuario> iniciarSesion(String correo, String clave){
        Optional<Usuario> optU = repository.findByCorreo(correo);
        if (optU.isPresent() && passwordEncoder.matches(clave, optU.get().getClave())) {
            return new RespuestaServidor<>(TIPO_AUTH, RPTA_OK, "Haz iniciado sesión correctamente", optU.get());
        } else {
            return new RespuestaServidor<>(TIPO_AUTH, RPTA_WARNING, "Correo o contraseña incorrecta", null);
        }
    }
    public RespuestaServidor<Usuario> guardarUsuario(Usuario u){
        boolean existe = u.getId() > 0 && repository.existsById(u.getId());
        u.setClave(passwordEncoder.encode(u.getClave()));
        Usuario usuarioGuardado = repository.save(u);
        String mensaje = existe ? "Datos del usuario actualizados" : "Usuario Registrado Correctamente";
        return new RespuestaServidor<>(TIPO_DATA, RPTA_OK, mensaje, usuarioGuardado);
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarTodosLosUsuarios();
    }

    public RespuestaServidor<Usuario> actualizarUsuario(ActualizarUsuarioDTO datos) {
        Optional<Usuario> usuarioOpt = repository.findById(datos.getIdUsuario());
        if (!usuarioOpt.isPresent()) {
            return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_ERROR, Resultado.OPERACION_ERRONEA, null);
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setCorreo(datos.getCorreo());
        Cliente cliente = usuario.getCliente();
        cliente.setNombreCompleto(datos.getNombreCompleto());
        cliente.setNumeroTelefonico(datos.getNumeroTelefonico());
        // Guardar cambios
        repository.save(usuario);
        // Más lógica si es necesario...

        return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_OK, Resultado.OPERACION_CORRECTA, usuario);
    }

    public RespuestaServidor<List<Usuario>> buscarPorNombre(String nombreCompleto) {
        List<Usuario> usuarios = repository.findByNombreCompleto(nombreCompleto);
        if (usuarios.isEmpty()) {
            return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_WARNING, "No se encontraron usuarios con ese nombre", null);
        }
        return new RespuestaServidor<>(Resultado.TIPO_DATA, Resultado.RPTA_OK, "Usuarios encontrados", usuarios);
    }

}
