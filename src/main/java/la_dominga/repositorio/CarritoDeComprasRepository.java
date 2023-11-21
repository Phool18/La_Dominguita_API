package la_dominga.repositorio;

import la_dominga.entidades.CarritoDeCompras;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoDeComprasRepository extends CrudRepository<CarritoDeCompras, Integer> {

    @Query("SELECT c FROM CarritoDeCompras c WHERE c.cliente.id = :idCliente")
    Iterable<CarritoDeCompras> devolverCompraPorCliente(@Param("idCliente") int idCliente);

}
