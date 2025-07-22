package com.user.service.controller;

import com.user.service.entity.User;
import com.user.service.exception.UserNotFoundException;
import com.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
               // .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id : " + id));(hedhi bl 9dimaaaa badelnaha beli tahtha )
        		.orElseThrow(() -> new UserNotFoundException(id));

    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé avec id : " + id);
        }
        userRepository.deleteById(id);
        return "Utilisateur avec id " + id + " a été supprimé avec succès.";
    }

}
