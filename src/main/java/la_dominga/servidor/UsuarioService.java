package la_dominga.servidor;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Usuario;
import la_dominga.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import static la_dominga.configuraciones.Resultado.*;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    public RespuestaServidor<Usuario> iniciarSesion(String correo, String clave){
        Optional<Usuario> optU = this.repository.iniciarSesion(correo, clave);
        return optU.map(usuario -> new RespuestaServidor<>(TIPO_AUTH, RPTA_OK, "Haz iniciado sesión correctamente", usuario))
                .orElseGet(() -> new RespuestaServidor<>(TIPO_AUTH, RPTA_WARNING, "Lo sentimos, ese usuario no existe", null));
    }
    public RespuestaServidor<Usuario> guardarUsuario(Usuario u){
        boolean existe = u.getId() > 0 && repository.existsById(u.getId());
        Usuario usuarioGuardado = this.repository.save(u);
        String mensaje = existe ? "Datos del usuario actualizados" : "Usuario Registrado Correctamente";
        return new RespuestaServidor<>(TIPO_DATA, RPTA_OK, mensaje, usuarioGuardado);
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarTodosLosUsuarios();
    }
}
