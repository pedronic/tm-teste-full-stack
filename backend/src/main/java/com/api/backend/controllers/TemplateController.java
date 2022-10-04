package com.api.backend.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }
}
