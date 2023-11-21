package la_dominga.controlador;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.dto.CrearPedidoDTO;
import la_dominga.entidades.dto.ImprimirPedidosDTO;
import la_dominga.servidor.CarritoDeComprasService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carritoDeCompras")
public class CarritoDeComprasController {

    private final CarritoDeComprasService carritoDeComprasService;

    public CarritoDeComprasController(CarritoDeComprasService carritoDeComprasService) {
        this.carritoDeComprasService = carritoDeComprasService;
    }

    @GetMapping("/misCompras/{idCliente}")
    public RespuestaServidor<List<ImprimirPedidosDTO>> obtenerMisCompras(@PathVariable int idCliente) {
        return this.carritoDeComprasService.devolverMisCompras(idCliente);
    }
    //GUARDAR PEDIDO
    @PostMapping
    public RespuestaServidor generarPedidoAlCliente(@RequestBody CrearPedidoDTO crearPedidoDTO){
        return this.carritoDeComprasService.generarPedido(crearPedidoDTO);
    }

}
