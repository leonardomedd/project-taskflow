package com.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskflow.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}