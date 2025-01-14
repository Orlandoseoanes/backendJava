package Tesis.tesisUnir.services;

import Tesis.tesisUnir.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    List<User> findAll();

    User save(User user);

    User update(User user);

    Optional<User> findById(String id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String correo);

    void deleteById(String id);
}
