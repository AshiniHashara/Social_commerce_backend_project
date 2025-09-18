package com.socialCommerce.backend_social.controller;

import com.socialCommerce.backend_social.model.AuthResponse;
import com.socialCommerce.backend_social.model.User;
import com.socialCommerce.backend_social.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/Register")
    public User Register(@Valid @RequestBody User user){

        return service.register(user);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {
        return service.verify(user);
    }


//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody User user) {
//        return service.verify(user);
//    }

}
