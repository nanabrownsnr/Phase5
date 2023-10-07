package com.backend.Controllers;

import com.backend.Entity.User;
import com.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000"})
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public List<User> findUserByUsername(@PathVariable(value = "username") String username) {

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        List users = new ArrayList();

        if (user.isPresent()) {
            users.add(user);
        }
        return users;
    }

    @PostMapping
    public User insertUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
