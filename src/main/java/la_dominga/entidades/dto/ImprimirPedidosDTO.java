package la_dominga.entidades.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import la_dominga.entidades.CarritoDeCompras;
import la_dominga.entidades.DatosCompra;

public class ImprimirPedidosDTO {

    private CarritoDeCompras carritoDeCompras ;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
