package la_dominga.repositorio;

import la_dominga.entidades.DatosCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DatosCompraRepository extends CrudRepository<DatosCompra, Integer> {

    @Query("SELECT DC FROM DatosCompra DC WHERE DC.carritoDeCompras.id=:idDeLaCompra")
    Iterable<DatosCompra> devolverComprasPorId(int idDeLaCompra);
}
