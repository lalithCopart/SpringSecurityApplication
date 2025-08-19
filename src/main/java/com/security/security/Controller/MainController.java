package com.security.security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/public")
    public String PublicString() {
        return "This is public";
    }

    @GetMapping("/private")
    public String PrivateString() {
        return "This is private";
    }
}
