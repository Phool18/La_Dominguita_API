package la_dominga.entidades.dto;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonProperty;
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
import la_dominga.entidades.CarritoDeCompras;
import la_dominga.entidades.DatosCompra;

public class ImprimirPedidosDTO {

    private CarritoDeCompras carritoDeCompras ;
<<<<<<< HEAD

=======
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
    private Iterable<DatosCompra> detallePedido;

    public ImprimirPedidosDTO() {
    }

    public ImprimirPedidosDTO(CarritoDeCompras carritoDeCompras, Iterable<DatosCompra> detallePedido) {
        this.carritoDeCompras = carritoDeCompras;
        this.detallePedido = detallePedido;
    }

    public CarritoDeCompras getCarritoDeCompras() {
        return carritoDeCompras;
    }

    public void setCarritoDeCompras(CarritoDeCompras carritoDeCompras) {
        this.carritoDeCompras = carritoDeCompras;
    }

    public Iterable<DatosCompra> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(Iterable<DatosCompra> detallePedido) {
        this.detallePedido = detallePedido;
    }
}
