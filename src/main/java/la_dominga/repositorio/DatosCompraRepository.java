package la_dominga.repositorio;

import la_dominga.entidades.DatosCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosCompraRepository extends CrudRepository<DatosCompra, Integer> {

    @Query("SELECT d FROM DatosCompra d WHERE d.carritoDeCompras.id = :idCarrito")
    Iterable<DatosCompra> devolverComprasPorId(@Param("idCarrito") int idCarrito);
=======

public interface DatosCompraRepository extends CrudRepository<DatosCompra, Integer> {

    @Query("SELECT DC FROM DatosCompra DC WHERE DC.carritoDeCompras.id=:idDeLaCompra")
    Iterable<DatosCompra> devolverComprasPorId(int idDeLaCompra);
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
}
