package la_dominga.entidades.dto;

import la_dominga.entidades.CarritoDeCompras;
import la_dominga.entidades.DatosCompra;

public class ImprimirPedidosDTO {

    private CarritoDeCompras carritoDeCompras ;
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
