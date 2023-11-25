package la_dominga.entidades.dto;

import la_dominga.entidades.CarritoDeCompras;
import la_dominga.entidades.DatosCompra;

import java.util.ArrayList;

public class ImprimirPedidosDTO {

    private CarritoDeCompras carritoDeCompras ;
    private Iterable<DatosCompra> detallePedido;


    public ImprimirPedidosDTO(CarritoDeCompras carritoDeCompras, Iterable<DatosCompra> detallePedido) {
        this.carritoDeCompras = carritoDeCompras;
        this.detallePedido = detallePedido;
    }
    public ImprimirPedidosDTO() {
        this.carritoDeCompras = new CarritoDeCompras();
        this.detallePedido = new ArrayList<>();
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
