package bg.softuni._gamestore.services.impl;

import bg.softuni._gamestore.data.entities.User;
import bg.softuni._gamestore.data.repositories.UserRepository;
import bg.softuni._gamestore.services.UserDto;
import bg.softuni._gamestore.services.UserService;
import bg.softuni._gamestore.utils.Validator;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Validator validator;
    private final ModelMapper mapper;


    private  User loginUser;

    public UserServiceImpl(UserRepository userRepository, Validator validator, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    public User getLoginUser() {
        return loginUser;
    }
    @Override
    public String registerUser(UserDto userDto) {
        if(!validator.isValid(userDto)){
            Set<ConstraintViolation<UserDto>> constraintViolationsSet = validator.validate(userDto);
            return constraintViolationsSet
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            return "Passwords do not match";
        }

        Optional<User> optional = userRepository.findUserByEmail(userDto.getEmail());
        if(optional.isPresent()){
            return "This email already exists";
        }
        User user = this.mapper.map(userDto,User.class);
        if(this.userRepository.count() == 0){
            user.setAdmin(true);
        }

        this.userRepository.saveAndFlush(user);
        return String.format("%s was registered",userDto.getFullName());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public String loginUser(String email, String password) {
        Optional<User> optionalUser = userRepository.findUserByEmailAndPassword(email,password);
        if(optionalUser.isEmpty()){
            return "Incorrect username / password";
        }
        loginUser = optionalUser.get();
        return String.format("Successfully logged in %s",loginUser.getFullName());
    }

    @Override
    public String logoutUser() {
        if(loginUser == null){
            return "Cannot log out. No user was logged in.";
        }
        String result = String.format("User %s successfully logged out",loginUser.getFullName());
        loginUser = null;
        return result;
    }
}
