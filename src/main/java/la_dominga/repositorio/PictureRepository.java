package la_dominga.repositorio;


import la_dominga.entidades.Picture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
    @Query("SELECT da FROM Picture da WHERE da.vigencia = 'A' AND da.eliminado = false")
    Iterable<Picture> list();

    @Query("SELECT da FROM Picture da WHERE da.nombreArchivo = :fileName AND da.vigencia = 'A' AND da.eliminado = false")
    Optional<Picture> findByFileName(@Param("fileName") String fileName);
}