package la_dominga.repositorio;

import la_dominga.entidades.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

@Repository
=======

>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c")
    Iterable<Categoria> listarCategorias();
<<<<<<< HEAD

    @Override
    <S extends Categoria> S save(S entity);

=======
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
}
