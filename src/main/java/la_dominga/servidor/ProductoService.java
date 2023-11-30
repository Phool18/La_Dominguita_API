package la_dominga.servidor;


import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.Producto;
import la_dominga.repositorio.ProductoRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

import static la_dominga.configuraciones.Resultado.*;

@Service
@Transactional
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository repository) {
        this.productoRepository = repository;
    }

    public RespuestaServidor<List<Producto>> listarProductosPorNombre(String nombre){
        List<Producto> productos = productoRepository.listarProductosPorNombre(nombre);
        return new RespuestaServidor<>(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, productos);
    }

    public RespuestaServidor<List<Producto>> listarProductosPorCategoria(int idCategoria){
        List<Producto> productos = productoRepository.listarProductosPorCategoria(idCategoria);
        return new RespuestaServidor<>(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, productos);
    }
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public RespuestaServidor<List<Producto>> listarProductosTop() {
        List<Producto> productos = productoRepository.listarProductosTop();
        return new RespuestaServidor<>(TIPO_DATA, RPTA_OK, "Productos Top obtenidos con éxito", productos);
    }
}
