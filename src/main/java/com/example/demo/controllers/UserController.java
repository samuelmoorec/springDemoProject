package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.repos.ProductRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepo userdao;
    private PasswordEncoder passwordEncoder;

    UserController(UserRepo userdao, PasswordEncoder passwordEncoder){
        this.userdao = userdao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String viewRegisterForm(){
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam(name = "first_name") String firstName,
            @RequestParam(name = "last_name") String lastName,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password

            ){

        User userToAdd = new User(firstName, lastName, email, username, passwordEncoder.encode(password));
        userdao.save(userToAdd);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String viewLoginForm(){
        return "user/login";
    }

}
