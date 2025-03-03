package com.taskflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taskflow.model.User;
import com.taskflow.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username não pode ser nulo ou vazio");
        }

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username '" + username + "' já existe");
        }

        User user = new User();
        user.setUsername(username);
        user.setPoints(0);
        return userRepository.save(user);
    }

    public void addPoints(Long userId, int points) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        user.setPoints(user.getPoints() + points);
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}