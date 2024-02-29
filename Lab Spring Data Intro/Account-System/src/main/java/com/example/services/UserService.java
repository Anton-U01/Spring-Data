package com.example.services;

import com.example.models.User;

public interface UserService {
    void registerUser(User user);

    User findUserById(long i);
}
