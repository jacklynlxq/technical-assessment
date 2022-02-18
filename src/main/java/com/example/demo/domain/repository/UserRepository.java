package com.example.demo.domain.repository;

import com.example.demo.domain.entitiy.User;

public interface UserRepository {
    User findByUserEmail(String email);
}
