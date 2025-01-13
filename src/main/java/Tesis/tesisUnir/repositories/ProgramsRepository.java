package Tesis.tesisUnir.repositories;

import Tesis.tesisUnir.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProgramsRepository extends JpaRepository<Program, String> {

    @Query("SELECT p FROM Program p WHERE p.name = :name")
    Optional<Program> findByName(@Param("name") String name);
}
