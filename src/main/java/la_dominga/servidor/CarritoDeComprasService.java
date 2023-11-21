package la_dominga.servidor;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.CarritoDeCompras;
import la_dominga.entidades.DatosCompra;
import la_dominga.entidades.dto.CrearPedidoDTO;
import la_dominga.entidades.dto.ImprimirPedidosDTO;
import la_dominga.repositorio.CarritoDeComprasRepository;
import la_dominga.repositorio.DatosCompraRepository;
import la_dominga.repositorio.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static la_dominga.configuraciones.Resultado.*;

@Service
@Transactional
public class CarritoDeComprasService {
    private final CarritoDeComprasRepository carritoDeComprasRepository;
    private final DatosCompraRepository datosCompraRepository;
    private final DatosCompraService datosCompraService;//
    private final ProductoRepository productoRepository;//

    public CarritoDeComprasService(CarritoDeComprasRepository carritoDeComprasRepository, DatosCompraRepository datosCompraRepository, DatosCompraService datosCompraService, ProductoRepository productoRepository) {
        this.carritoDeComprasRepository = carritoDeComprasRepository;
        this.datosCompraRepository = datosCompraRepository;
        this.datosCompraService = datosCompraService;
        this.productoRepository = productoRepository;
    }

    //METODO PARA GUARDAR EL PEDIDO DEL CLIENTE
    public RespuestaServidor generarPedido(CrearPedidoDTO crearPedidoDTO) {
        CarritoDeCompras carrito = crearPedidoDTO.getCarritoDeCompras();
        carrito.setFechaCompra(new java.sql.Date(new Date().getTime()));
        carrito.setCliente(crearPedidoDTO.getCliente());
        carritoDeComprasRepository.save(carrito);

        for (DatosCompra datosCompra : crearPedidoDTO.getInformacionDeLaVenta()) {
            datosCompra.setCarritoDeCompras(carrito); // Establecer la relación con el carrito
            productoRepository.gestionarVenta(datosCompra.getCantidad(), datosCompra.getProducto().getId());
        }

        datosCompraService.guardarInformacionDeLaVenta(crearPedidoDTO.getInformacionDeLaVenta()); // Guardar todos los detalles de compra

        return new RespuestaServidor(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, carrito);
    }
    //METODO PARA DEVOLVER EL PEDIDO DE UN CLIENTE
    public RespuestaServidor<List<ImprimirPedidosDTO>> devolverMisCompras(int idCli) {
        final List<ImprimirPedidosDTO> imprimirPedidos = new ArrayList<>();
        final Iterable<CarritoDeCompras> pedidos = carritoDeComprasRepository.devolverCompraPorCliente(idCli);
        pedidos.forEach(p -> {
            imprimirPedidos.add(new ImprimirPedidosDTO(p, datosCompraRepository.devolverComprasPorId(p.getId())));
        });
        return new RespuestaServidor<>(OPERACION_CORRECTA, RPTA_OK, "Petición Encontrada", imprimirPedidos);
    }
}
