package la_dominga.entidades.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import la_dominga.entidades.Compra;
import la_dominga.entidades.DatosCompra;

public class ImprimirPedidosDTO {

    private Compra compra ;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Iterable<DatosCompra> detallePedido;

    public ImprimirPedidosDTO() {
    }

    public ImprimirPedidosDTO(Compra compra, Iterable<DatosCompra> detallePedido) {
        this.compra = compra;
        this.detallePedido = detallePedido;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Iterable<DatosCompra> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(Iterable<DatosCompra> detallePedido) {
        this.detallePedido = detallePedido;
    }
}
