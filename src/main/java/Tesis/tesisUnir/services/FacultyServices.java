package Tesis.tesisUnir.services;

import Tesis.tesisUnir.entities.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyServices {

    List<Faculty> findAll();

    Faculty save(Faculty faculty);

    Faculty update(String id, Faculty faculty);

    Optional<Faculty> findById(String id);

    Optional<Faculty> findByName(String name);

    void deleteById(String id);
}
