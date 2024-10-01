package org.example.springbootcarservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BasicController {
    @GetMapping("/")
    public String index() {
        return "richard";
    }
}