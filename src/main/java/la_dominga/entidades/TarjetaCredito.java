package la_dominga.entidades;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Year;

@Entity
public class TarjetaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Size(min = 16, max = 16, message = "Error")
    private String numeroTarjeta;

    @Column()
    @Size(min = 1, max = 100, message = "El titular debe tener entre 1 y 100 caracteres.")
    private String titular;

    @Column
    @Pattern(regexp = "^(0[1-9]|1[0-2])/(\\d{2})$", message = "El formato de mes_anio debe ser MM/yy")
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


    public boolean isValidDate() {
        try {
            // Intenta analizar la cadena mes_anio en un objeto LocalDate
            String[] parts = mes_anio.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt("20" + parts[1]); // Suponiendo que siempre es en el siglo XXI

            LocalDate inputDate = LocalDate.of(Year.now().getValue(), month, 1);
            LocalDate currentDate = LocalDate.now();

            return !inputDate.isBefore(currentDate);
        } catch (Exception e) {
            return false; // Error al analizar la cadena o fecha en el pasado
        }
    }
}
