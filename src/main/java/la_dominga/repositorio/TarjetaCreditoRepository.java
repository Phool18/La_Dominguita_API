package la_dominga.repositorio;

import la_dominga.entidades.TarjetaCredito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TarjetaCreditoRepository extends CrudRepository<TarjetaCredito, Integer> {

    @Query("SELECT t FROM TarjetaCredito t WHERE t.numeroTarjeta = :numeroTarjeta AND t.titular = :titular AND t.cvv = :cvv AND t.mes_anio = :mesAnio")
    Optional<TarjetaCredito> validarTarjeta(@Param("numeroTarjeta") String numeroTarjeta, @Param("titular") String titular, @Param("cvv") String cvv, @Param("mesAnio") String mesAnio);

    List<TarjetaCredito> findByUsuarioId(int usuarioId);

}
