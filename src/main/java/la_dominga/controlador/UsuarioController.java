package la_dominga.controlador;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Usuario;
import la_dominga.entidades.dto.ActualizarUsuarioDTO;
import la_dominga.servidor.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/guardar")
    public ResponseEntity<RespuestaServidor<Usuario>> guardarUsuario(@Valid @RequestBody Usuario usuario) {
        RespuestaServidor<Usuario> respuesta = service.guardarUsuario(usuario);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<RespuestaServidor<Usuario>> iniciarSesion(@RequestParam String correo, @RequestParam String clave) {
        RespuestaServidor<Usuario> respuesta = service.iniciarSesion(correo, clave);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = service.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/actualizar")
    public RespuestaServidor<Usuario> actualizarUsuario(@RequestBody ActualizarUsuarioDTO datos) {
        return service.actualizarUsuario(datos);
    }

    @GetMapping("/buscarPorNombre/{nombreCompleto}")
    public RespuestaServidor<List<Usuario>> buscarUsuarioPorNombre(@PathVariable String nombreCompleto) {
        return service.buscarPorNombre(nombreCompleto);
    }

}
