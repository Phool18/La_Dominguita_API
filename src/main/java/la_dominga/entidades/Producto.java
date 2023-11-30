package la_dominga.entidades;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String nombre;

    @Column
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres.")
    private String descripcion;

    @Column
    @NotNull(message = "El precio no puede ser nulo.")
    @Min(value = 0, message = "El precio no puede ser menor que cero.")
    private double precio;

    @Column
    @NotNull(message = "La cantidad en stock no puede ser nula.")
    @Min(value = 0, message = "La cantidad en stock no puede ser menor que cero.")
    private int cantidadEnStock;

    @OneToOne
    @NotNull(message = "La categoría no puede ser nula.")
    private Categoria categoria;

    @OneToOne
    private Picture foto;

    @Column
    private boolean top;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public Picture getFoto() {
        return foto;
    }

    public void setFoto(Picture foto) {
        this.foto = foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }
}
