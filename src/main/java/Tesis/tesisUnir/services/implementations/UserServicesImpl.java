package Tesis.tesisUnir.services.implementations;

import Tesis.tesisUnir.entities.User;
import Tesis.tesisUnir.repositories.UserRepository;
import Tesis.tesisUnir.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        if (user.getCorreo() == null || user.getContrasena() == null) {
            throw new IllegalArgumentException("El correo y la contraseña son obligatorios");
        }

        Optional<User> existingUser = findByEmail(user.getCorreo());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        try {
            user.setContrasena(passwordEncoder.encode(user.getContrasena()));
            return repository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<User> findByDocument(String document) {
        return repository.findByDocument(document);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }


}
