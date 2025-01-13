package Tesis.tesisUnir.controllers;

import Tesis.tesisUnir.entities.User;
import Tesis.tesisUnir.services.implementations.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServicesImpl userServices;

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
}
