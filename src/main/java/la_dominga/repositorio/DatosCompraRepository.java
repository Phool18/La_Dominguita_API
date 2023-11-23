package la_dominga.repositorio;

import la_dominga.entidades.DatosCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosCompraRepository extends CrudRepository<DatosCompra, Integer> {

    @Query("SELECT d FROM DatosCompra d WHERE d.carritoDeCompras.id = :idCarrito")
    Iterable<DatosCompra> devolverComprasPorId(@Param("idCarrito") int idCarrito);
}