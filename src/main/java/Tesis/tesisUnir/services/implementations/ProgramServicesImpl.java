package Tesis.tesisUnir.services.implementations;

import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.repositories.ProgramsRepository;
import Tesis.tesisUnir.services.ProgramServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServicesImpl implements ProgramServices {
    @Autowired
    private ProgramsRepository programRepository;

    @Override
    public List<Program> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Program save(Program program) {
        return programRepository.save(program);
    }

    @Override
    public Program update(Program program) {
        if (program.getId() == null || program.getId().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío para actualizar.");
        }

        Optional<Program> existingProgram = programRepository.findById(program.getId());
        if (existingProgram.isEmpty()) {
            throw new IllegalArgumentException("El programa con ID " + program.getId() + " no existe.");
        }

        Program programToUpdate = existingProgram.get();
        programToUpdate.setName(program.getName());
        programToUpdate.setFaculty(program.getFaculty());

        return programRepository.save(programToUpdate);
    }

    @Override
    public Optional<Program> findById(String id) {
        return programRepository.findById(id);
    }

    @Override
    public Optional<Program> findByName(String name) {
        return programRepository.findByName(name);
    }

    @Override
    public void deleteById(String id) {
        programRepository.deleteById(id);
    }
}