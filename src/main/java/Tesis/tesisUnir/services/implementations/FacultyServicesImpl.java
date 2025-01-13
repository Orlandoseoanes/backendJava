package Tesis.tesisUnir.services.implementations;

import Tesis.tesisUnir.entities.Faculty;
import Tesis.tesisUnir.repositories.FacultiesRepository;
import Tesis.tesisUnir.services.FacultyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServicesImpl implements FacultyServices {
    @Autowired
    FacultiesRepository facultiesRepository;

    @Override
    public List<Faculty> findAll() {
        return facultiesRepository.findAll();
    }

    @Override
    public Faculty save(Faculty faculty) {
        return facultiesRepository.save(faculty);
    }

    @Override
    public Faculty update(String id, Faculty faculty) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío para actualizar.");
        }

        Optional<Faculty> existingFaculty = facultiesRepository.findById(id);
        if (existingFaculty.isEmpty()) {
            throw new IllegalArgumentException("La facultad con ID " + id + " no existe.");
        }

        Faculty facultyToUpdate = existingFaculty.get();
        facultyToUpdate.setName(faculty.getName());

        return facultiesRepository.save(facultyToUpdate);
    }

    @Override
    public Optional<Faculty> findById(String id) {
        return facultiesRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        facultiesRepository.deleteById(id);
    }
}