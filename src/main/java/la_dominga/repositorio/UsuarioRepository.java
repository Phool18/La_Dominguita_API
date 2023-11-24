package la_dominga.repositorio;


import la_dominga.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query("SELECT U FROM Usuario U WHERE U.correo = :correo AND U.clave = :password")
    Optional<Usuario> iniciarSesion(@Param("correo") String correo, @Param("password") String password);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.correo = :correo")
    boolean existeMismoCorreo(@Param("correo") String correo);
    @Query("SELECT U FROM Usuario U WHERE U.correo = :correo")
    Optional<Usuario> findByCorreo(@Param("correo") String correo);

    @Query("SELECT u FROM Usuario u")
    List<Usuario> listarTodosLosUsuarios();

    @Query("SELECT u FROM Usuario u WHERE u.cliente.nombreCompleto LIKE %:nombreCompleto%")
    List<Usuario> findByNombreCompleto(@Param("nombreCompleto") String nombreCompleto);

}