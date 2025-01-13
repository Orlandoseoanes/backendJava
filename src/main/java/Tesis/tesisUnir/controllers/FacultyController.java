package Tesis.tesisUnir.controllers;

import Tesis.tesisUnir.entities.Faculty;
import Tesis.tesisUnir.services.implementations.FacultyServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {
    @Autowired
    private FacultyServicesImpl facultyServices;

    @PostMapping
    public ResponseEntity<Faculty> save(@RequestBody Faculty faculty) {
        try {
            Faculty data = facultyServices.save(faculty);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> findAll() {
        List<Faculty> dataList = facultyServices.findAll();
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> findById(@PathVariable String id) {
        Optional<Faculty> data = facultyServices.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> update(@PathVariable("id") String id, @RequestBody Faculty faculty) {
        try {
            Faculty updatedFaculty = facultyServices.update(id, faculty);
            return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        try {
            facultyServices.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
