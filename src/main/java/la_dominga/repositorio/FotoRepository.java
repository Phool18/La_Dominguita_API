package la_dominga.repositorio;


import la_dominga.entidades.Foto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FotoRepository extends CrudRepository<Foto, Long> {
    @Query("SELECT da FROM Foto da WHERE da.vigencia = 'A' AND da.eliminado = false")
    Iterable<Foto> list();

    @Query("SELECT da FROM Foto da WHERE da.nombreArchivo = :fileName AND da.vigencia = 'A' AND da.eliminado = false")
    Optional<Foto> findByFileName(@Param("fileName") String fileName);
}