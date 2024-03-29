package la_dominga.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 200, message = "El nombre sobrepasa lo permitido, por favor comunicarse con un administrador")
    private String nombreCompleto;

    @NotNull
    @Pattern(regexp = "[0-9]+", message = "El número telefónico solo debe contener dígitos")
    @Size(min = 9, max = 9, message = "El número telefónico debe tener exactamente 9 dígitos")
    private String numeroTelefonico;

    @OneToOne
    private Picture foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public Picture getFoto() {
        return foto;
    }

    public void setFoto(Picture foto) {
        this.foto = foto;
    }
    public String getNombreCompletoCliente() {
        return this.nombreCompleto != null ? this.nombreCompleto : "-----";
    }
}
