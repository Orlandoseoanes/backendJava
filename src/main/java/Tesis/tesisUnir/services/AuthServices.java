package Tesis.tesisUnir.services;

import Tesis.tesisUnir.entities.User;

public interface AuthServices {
    boolean authenticateUser(String email, String password);
    User register(User user);
}
