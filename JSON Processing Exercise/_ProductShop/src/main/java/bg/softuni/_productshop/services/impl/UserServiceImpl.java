package bg.softuni._productshop.services.impl;

import bg.softuni._productshop.data.repositories.UserRepository;
import bg.softuni._productshop.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
