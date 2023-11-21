package la_dominga.entidades;

import javax.persistence.*;
<<<<<<< HEAD
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
=======
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
<<<<<<< HEAD
    @NotBlank(message = "El nombre de la categoría no puede estar vacío.")
    @Size(max = 100, message = "El nombre de la categoría no puede superar los 100 caracteres.")
=======
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
    private String nombre;
    @OneToOne
    private Foto foto;

    public Categoria() {
    }

    public Categoria(int id, String nombre, Foto foto) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
}
