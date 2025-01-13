package Tesis.tesisUnir.controllers;

import Tesis.tesisUnir.entities.Faculty;
import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.services.implementations.FacultyServicesImpl;
import Tesis.tesisUnir.services.implementations.ProgramServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramServicesImpl programServices;

    @Autowired
    private FacultyServicesImpl facultyServices;

    @GetMapping
    public List<Program> getAllPrograms() {
        return programServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgramById(@PathVariable String id) {
        Optional<Program> program = programServices.findById(id);
        return program.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Program> getProgramByName(@RequestParam String name) {
        Optional<Program> program = programServices.findByName(name.toLowerCase());
        return program.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        Optional<Faculty> faculty = facultyServices.findByName(program.getFaculty().getName());
        if (faculty.isEmpty()) {
            Faculty newFaculty = facultyServices.save(program.getFaculty());
            program.setFaculty(newFaculty);
        } else {
            program.setFaculty(faculty.get());
        }
        Program savedProgram = programServices.save(program);
        return ResponseEntity.status(201).body(savedProgram);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable String id, @RequestBody Program program) {
        if (!id.equals(program.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(programServices.update(program));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable String id) {
        programServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}