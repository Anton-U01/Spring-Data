package bg.softuni._productshop.services.impl;

import bg.softuni._productshop.data.dtos.UsersWithSoldProductsDto;
import bg.softuni._productshop.data.repositories.UserRepository;
import bg.softuni._productshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UsersWithSoldProductsDto> getUsersWithSoldProducts() {
        return this.userRepository.findUsersWithSoldProducts()
                .orElseThrow()
                .stream()
                .map(u -> mapper.map(u, UsersWithSoldProductsDto.class))
                .toList();
    }
}
