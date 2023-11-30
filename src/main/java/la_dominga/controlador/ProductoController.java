package la_dominga.controlador;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Producto;
import la_dominga.servidor.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    public ProductoController(ProductoService service) {
        this.productoService = service;
    }

    @GetMapping("/nombre/{nombre}")
    public RespuestaServidor listarProductosPorNombre(@PathVariable String nombre) {
        return this.productoService.listarProductosPorNombre(nombre);
    }
    @GetMapping("/categoria/{idCategoria}")
    public RespuestaServidor listarProductosPorCategoria(@PathVariable int idCategoria){
        return this.productoService.listarProductosPorCategoria(idCategoria);
    }

    @PostMapping("/guardar")
    public RespuestaServidor<Producto> agregarProducto(@Valid @RequestBody Producto producto) {
        Producto productoGuardado = productoService.guardarProducto(producto);
        return new RespuestaServidor<>("Success", 200, "Producto guardado con éxito", productoGuardado);
    }

    @GetMapping("/top")
    public RespuestaServidor<List<Producto>> listarProductosTop() {
        return productoService.listarProductosTop();
    }
    @GetMapping
    public RespuestaServidor<List<Producto>> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaServidor<Producto>> obtenerProductoPorId(@PathVariable int id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(new RespuestaServidor<>("Producto", 1, "Producto encontrado", producto.get()));
        } else {
            return ResponseEntity.ok(new RespuestaServidor<>("Producto", 0, "Producto no encontrado", null));
        }
    }
}
