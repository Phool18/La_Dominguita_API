package la_dominga.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "compra_id")
    private CarritoDeCompras carritoDeCompras;
    @Column
    private double monto;
    @Column
    @JsonFormat(pattern = "dd-MM-YYYY",timezone = "America/Lima")
    private Date fechaPago;


    public Pago() {
    }

    public Pago(int id, CarritoDeCompras carritoDeCompras, double monto, Date fechaPago) {
        this.id = id;
        this.carritoDeCompras = carritoDeCompras;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public CarritoDeCompras getCarritoDeCompras() {
        return carritoDeCompras;
    }

    public void setCarritoDeCompras(CarritoDeCompras carritoDeCompras) {
        this.carritoDeCompras = carritoDeCompras;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

}
