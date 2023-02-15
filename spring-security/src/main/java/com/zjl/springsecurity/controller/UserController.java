package com.zjl.springsecurity.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("/hello")
    public String Hello(){
        return "Hello!";
    }

    @GetMapping("/update")
    @PostAuthorize("hasAnyAuthority('admins')")
    public String update(){
        return "update!";
    }
}
