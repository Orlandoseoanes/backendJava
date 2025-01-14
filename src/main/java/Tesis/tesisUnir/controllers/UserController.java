package Tesis.tesisUnir.controllers;

import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.entities.User;
import Tesis.tesisUnir.services.implementations.ProgramServicesImpl;
import Tesis.tesisUnir.services.implementations.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServicesImpl userServices;

    @Autowired
    private ProgramServicesImpl programServices;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Optional<Program> program = programServices.findByName(user.getProgram().getName());
        if (program.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            user.setProgram(program.get());
        }

        User savedUser = userServices.save(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> dataList = userServices.findAll();
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }

    @GetMapping("/search/email")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        Optional<User> user = userServices.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
