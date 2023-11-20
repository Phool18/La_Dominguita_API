package la_dominga.repositorio;

import la_dominga.entidades.TarjetaCredito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TarjetaRepository extends CrudRepository<TarjetaCredito, Integer> {

    @Query("SELECT t FROM TarjetaCredito t WHERE t.titular = :titular AND t.cvv = :cvv AND t.mes_anio = :mesAnio")
    Optional<TarjetaCredito> validarTarjeta(@Param("titular") String titular, @Param("cvv") String cvv, @Param("mesAnio") String mesAnio);
}
