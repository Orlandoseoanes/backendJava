package Tesis.tesisUnir.services.implementations;

import Tesis.tesisUnir.entities.Program;
import Tesis.tesisUnir.entities.User;
import Tesis.tesisUnir.repositories.ProgramsRepository;
import Tesis.tesisUnir.repositories.UserRepository;
import Tesis.tesisUnir.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServicesImpl implements AuthServices {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ProgramsRepository programRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public boolean authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        return passwordEncoder.matches(password, user.getContrasena());
    }

    @Override
    public User register(User user) {
        if (userRepository.findByDocument(user.getCedula()).isPresent()) {
            throw new IllegalArgumentException("La cédula ya está registrada.");
        }

        if (userRepository.findByUsername(user.getUsuario()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya está registrado.");
        }

        if (userRepository.findByEmail(user.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        Optional<Program> program = programRepository.findByName(user.getProgram().getName());
        if (program.isEmpty()) {
            throw new IllegalArgumentException("El programa no existe.");
        } else {
            user.setProgram(program.get());
        }

        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        return userRepository.save(user);
    }
}
