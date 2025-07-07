package org.example.demor2dbc.service;


import lombok.RequiredArgsConstructor;
import org.example.demor2dbc.entity.User;
import org.example.demor2dbc.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Mono<User> getUser(Long id){
        return userRepository.findById(id);
    }

}
