package org.example.demojpa.service;

import lombok.AllArgsConstructor;
import org.example.demojpa.entity.User;
import org.example.demojpa.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow();
    }

}
