package com.viheakode.backend.viheakode_core_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String welcome(){
        return "App is running";
    }

    @GetMapping("/home")
    public String home(){
        return "Home page";
    }

    @GetMapping("/user")
    public String user(){
        return "User page";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin page";
    }

    @GetMapping("/anonymous")
    public String anonymous(){
        return "Anonymous page";
    }
}
