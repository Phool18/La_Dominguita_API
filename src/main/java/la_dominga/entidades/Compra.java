package la_dominga.entidades;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @Fetch(FetchMode.JOIN)
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @Column
    private double total;

    @Enumerated(EnumType.STRING)
    private EstadoCompra estado;

    public Compra() {
    }


    public Compra(int id, Usuario usuario, Date fechaCompra, double total, EstadoCompra estado) {
        this.id = id;
        this.usuario = usuario;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }
}
