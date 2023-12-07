package la_dominga.controlador;

import la_dominga.configuraciones.RespuestaServidor;
import la_dominga.entidades.DatosCompra;
import la_dominga.entidades.dto.CrearPedidoDTO;
import la_dominga.entidades.dto.ImprimirPedidosDTO;
import la_dominga.servidor.BoletaService;
import la_dominga.servidor.CarritoDeComprasService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/carritoDeCompras")
public class CarritoDeComprasController {

    private final CarritoDeComprasService carritoDeComprasService;
    private final BoletaService boletaService;

    public CarritoDeComprasController(CarritoDeComprasService carritoDeComprasService, BoletaService boletaService) {
        this.carritoDeComprasService = carritoDeComprasService;
        this.boletaService = boletaService;
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
    @GetMapping("/detalles/{idCarrito}")
    public RespuestaServidor<List<DatosCompra>> obtenerDetallesCarrito(@PathVariable int idCarrito) {
        return carritoDeComprasService.obtenerDetallesPorCarrito(idCarrito);
    }

    @GetMapping("/boleta/{idCompra}")
    public void generarBoleta(@PathVariable int idCompra, HttpServletResponse response) {
        try {
            ImprimirPedidosDTO datosPedido = carritoDeComprasService.obtenerDatosPedido(idCompra);
            byte[] pdfBytes = boletaService.generarBoletaPdf(datosPedido);

            // Configurar los encabezados de la respuesta
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=boleta_" + idCompra + ".pdf");
            response.setContentLength(pdfBytes.length);

            // Escribir los bytes del PDF en el cuerpo de la respuesta
            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace(); // Aquí deberías manejar la excepción adecuadamente
        }
    }

}
