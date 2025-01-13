package Tesis.tesisUnir.services.implementations;

import Tesis.tesisUnir.entities.User;
import Tesis.tesisUnir.repositories.UserRepository;
import Tesis.tesisUnir.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
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
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
