package la_dominga.repositorio;

import la_dominga.entidades.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c")
    Iterable<Categoria> listarCategorias();

    @Override
    <S extends Categoria> S save(S entity);
}
