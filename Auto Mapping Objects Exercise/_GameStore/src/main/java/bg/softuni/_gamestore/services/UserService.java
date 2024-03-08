package bg.softuni._gamestore.services;

import bg.softuni._gamestore.data.entities.User;

import java.util.Optional;

public interface UserService {
    String registerUser(UserDto userDto);

    Optional<User> findUserByEmail(String email);

    String loginUser(String email, String password);

    String logoutUser();

    User getLoginUser();
}
