package la_dominga.entidades.dto;

import la_dominga.entidades.Cliente;

public class ActualizarUsuarioDTO {
    private int id;
    private String correo;
    private Cliente cliente;

    public ActualizarUsuarioDTO() {
    }

    public ActualizarUsuarioDTO(int id, String correo, Cliente cliente) {
        this.id = id;
        this.correo = correo;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
