package com.saritepe.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}
