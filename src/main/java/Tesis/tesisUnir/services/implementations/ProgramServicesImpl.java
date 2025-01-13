package Tesis.tesisUnir.services.implementations;

import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.services.ProgramServices;

import java.util.List;
import java.util.Optional;

public class ProgramServicesImpl implements ProgramServices {
    @Override
    public List<Program> findAll() {
        return List.of();
    }

    @Override
    public Program save(Program program) {
        return null;
    }

    @Override
    public Program update(Program program) {
        return null;
    }

    @Override
    public Optional<Program> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {

    }
}
