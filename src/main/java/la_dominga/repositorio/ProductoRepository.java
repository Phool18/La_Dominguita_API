package la_dominga.repositorio;

import la_dominga.entidades.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    List<Producto> listarProductosPorNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM Producto p WHERE p.categoria.id = :idC")
    List<Producto> listarProductosPorCategoria(@Param("idC") int idCategoria);

    @Modifying
    @Query("UPDATE Producto p SET p.cantidadEnStock = p.cantidadEnStock - :cant WHERE p.id = :id")
    void gestionarVenta(@Param("cant") int cantidad, @Param("id") int id);
<<<<<<< HEAD




=======
>>>>>>> e5fd409af7e72c79adb0df104c37fa23609c9c30
}
