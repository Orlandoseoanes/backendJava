package Tesis.tesisUnir.repositories;

import Tesis.tesisUnir.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FacultiesRepository extends JpaRepository<Faculty, String> {

    @Query("SELECT f FROM Faculty f WHERE f.name = :name")
    Optional<Faculty> findByName(@Param("name") String name);
}
