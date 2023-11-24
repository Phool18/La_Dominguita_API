package la_dominga.controlador;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Usuario;
import la_dominga.entidades.dto.ActualizarUsuarioDTO;
import la_dominga.servidor.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping
    public RespuestaServidor save(@Valid @RequestBody Usuario u){
        return this.service.registrarUsuario(u);
    }

    @PostMapping("/iniciarSesion")
    public RespuestaServidor<Usuario> iniciarSesion(@RequestParam String correo, @RequestParam String clave) {
        return service.iniciarSesion(correo, clave);
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
