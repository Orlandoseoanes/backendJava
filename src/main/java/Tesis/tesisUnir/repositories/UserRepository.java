package Tesis.tesisUnir.repositories;

import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.nombre = :name")
    Optional<User> findByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.correo = :correo")
    Optional<User> findByEmail(@Param("correo") String correo);

    @Query("SELECT u FROM User u WHERE u.usuario = :usuario")
    Optional<User> findByUsername(@Param("usuario") String usuario);

    @Query("SELECT u FROM User u WHERE u.cedula = :cedula")
    Optional<User> findByDocument(@Param("cedula") String cedula);
}
