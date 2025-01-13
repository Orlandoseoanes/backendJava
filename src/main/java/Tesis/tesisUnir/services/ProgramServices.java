package Tesis.tesisUnir.services;


import Tesis.tesisUnir.entities.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramServices {
    List<Program> findAll();

    Program save(Program program);

    Program update(Program program);

    Optional<Program> findById(String id);

    void deleteById(String id);
}
