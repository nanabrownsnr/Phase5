package com.backend.Controllers;

import com.backend.Entity.Admin;
import com.backend.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000"})
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/{username}")
    public List<Admin> findUserByUsername(@PathVariable(value = "username") String username) {

        Optional<Admin> admin = Optional.ofNullable(adminRepository.findByUsername(username));
        List admins = new ArrayList();

        if (admin.isPresent()) {
            admins.add(admin);
        }
        return admins;
    }
}
