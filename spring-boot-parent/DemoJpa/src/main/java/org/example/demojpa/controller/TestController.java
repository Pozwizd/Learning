package org.example.demojpa.controller;

import lombok.AllArgsConstructor;
import org.example.demojpa.entity.User;
import org.example.demojpa.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@AllArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping("/getUserByJpa/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

}
