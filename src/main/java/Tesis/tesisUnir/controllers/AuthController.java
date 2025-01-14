package Tesis.tesisUnir.controllers;

import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.entities.User;
import Tesis.tesisUnir.entities.dtos.LoginDto;
import Tesis.tesisUnir.entities.dtos.RegisterDto;
import Tesis.tesisUnir.services.implementations.AuthServicesImpl;
import Tesis.tesisUnir.services.implementations.ProgramServicesImpl;
import Tesis.tesisUnir.services.implementations.UserServicesImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServicesImpl authService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        boolean authenticated = authService.authenticateUser(loginDto.getEmail(), loginDto.getPassword());

        if (authenticated) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto registerDto) {
        try {
            User user = new User();
            user.setCedula(registerDto.getCedula());
            user.setNombre(registerDto.getNombre());
            user.setApellido(registerDto.getApellido());
            user.setUsuario(registerDto.getUsuario());
            user.setCorreo(registerDto.getCorreo());
            user.setContrasena(registerDto.getContrasena());
            user.setRol(registerDto.getRol());
            user.setProgram(registerDto.getProgram());

            authService.register(user);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar el usuario");
        }
    }
}
