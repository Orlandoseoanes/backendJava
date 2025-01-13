package Tesis.tesisUnir.repositories;

import Tesis.tesisUnir.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramsRepository extends JpaRepository<Program, String> {
}
