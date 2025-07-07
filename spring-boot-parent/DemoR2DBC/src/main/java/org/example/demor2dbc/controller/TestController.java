package org.example.demor2dbc.controller;

import lombok.AllArgsConstructor;
import org.example.demor2dbc.entity.User;
import org.example.demor2dbc.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping("/getUserByR2dbc/{id}")
    public Mono<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

}
