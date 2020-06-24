package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public IndexController(){}


    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
}


