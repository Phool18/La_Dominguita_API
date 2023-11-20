package la_dominga.entidades.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import la_dominga.entidades.Cliente;
import la_dominga.entidades.Compra;
import la_dominga.entidades.DatosCompra;

public class CrearPedidoDTO {
    private Compra compra ;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Iterable<DatosCompra> detallePedidos;
    private Cliente cliente;

    public CrearPedidoDTO() {
    }

    public CrearPedidoDTO(Compra compra, Iterable<DatosCompra> detallePedidos, Cliente cliente) {
        this.compra = compra;
        this.detallePedidos = detallePedidos;
        this.cliente = cliente;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Iterable<DatosCompra> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(Iterable<DatosCompra> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
