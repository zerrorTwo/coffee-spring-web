package com.nghia.coffee_spring_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String getTest() {
        return "admin/test";
    }
}
