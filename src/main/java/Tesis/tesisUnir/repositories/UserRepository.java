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

    @Query("SELECT u FROM User u WHERE u.correo = :name")
    Optional<User> findByEmail(@Param("email") String name);
}
