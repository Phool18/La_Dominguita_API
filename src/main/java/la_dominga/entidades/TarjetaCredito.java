package la_dominga.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
@Entity
public class TarjetaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
<<<<<<< HEAD
    @Size(min = 16, max = 16, message = "Error")
    private String numeroTarjeta;

    @Column()
    @Size(min = 1, max = 100, message = "El titular debe tener entre 1 y 100 caracteres.")
=======
    @Size(min = 1, max = 50, message = "Error")
    private String numeroTarjeta;

    @Column()
    @Size(min = 16, max = 16, message = "Error")
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
    private String titular;

    @Column
    private String mes_anio;

    @Column
    @Size(min = 3, max = 3, message = "Error")
    private String cvv;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public TarjetaCredito() {
    }

    public TarjetaCredito(int id, String numeroTarjeta, String titular, String mes_anio, String cvv, Usuario usuario) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.mes_anio = mes_anio;
        this.cvv = cvv;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getMes_anio() {
        return mes_anio;
    }

    public void setMes_anio(String mes_anio) {
        this.mes_anio = mes_anio;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
