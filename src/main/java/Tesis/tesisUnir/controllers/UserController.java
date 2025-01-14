package Tesis.tesisUnir.controllers;

import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.entities.User;
import Tesis.tesisUnir.services.implementations.ProgramServicesImpl;
import Tesis.tesisUnir.services.implementations.UserServicesImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Data
class LoginRequest {
    private String email;
    private String contrasena;
}


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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest credentials) {
        System.out.println("Iniciando sesión con: " + credentials);

        boolean isAuthenticated = userServices.authenticateUser(credentials.getEmail(), credentials.getContrasena());

        if (!isAuthenticated) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Credenciales incorrectas"));
        }

        Optional<User> user = userServices.findByEmail(credentials.getEmail());

        if (user.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Usuario no encontrado"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Inicio de sesión exitoso");
        response.put("status", 200);
        response.put("usuario", user.get());

        return ResponseEntity.ok(response);
    }

}
