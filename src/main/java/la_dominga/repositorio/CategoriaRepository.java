package la_dominga.repositorio;

import la_dominga.entidades.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

    @Override
    <S extends Categoria> S save(S entity);
}
