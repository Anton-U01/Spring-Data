package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(long i) {
        Optional<User> optional = this.userRepository.findById(i);
        if(optional.isPresent()){
            return optional.get();
        }
        System.out.println("No such user with id " + i);
        return null;
    }
}
