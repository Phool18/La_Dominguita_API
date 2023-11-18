package la_dominga.repositorio;


import la_dominga.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query("SELECT U FROM Usuario U WHERE U.correo=:correo AND U.clave=:password")
    Optional<Usuario> iniciarSesion(String correo, String password);
}