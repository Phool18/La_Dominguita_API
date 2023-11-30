package la_dominga.controlador;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Categoria;
import la_dominga.servidor.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;


    public CategoriaController(CategoriaService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<RespuestaServidor<List<Categoria>>> listarCategorias() {
        RespuestaServidor<List<Categoria>> respuesta = service.listarTodasLasCategorias();
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public RespuestaServidor<Categoria> agregarCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria categoriaGuardada = service.guardarCategoria(categoria);
        return new RespuestaServidor<>("Success", 200, "Categoría guardada con éxito", categoriaGuardada);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaServidor<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new RespuestaServidor<>("Error", 400, "Errores de validación", errors), HttpStatus.BAD_REQUEST);
    }
}