package bg.softuni._productshop.services;

import bg.softuni._productshop.data.dtos.UsersWithSoldProductsDto;

import java.util.List;

public interface UserService {
    List<UsersWithSoldProductsDto> getUsersWithSoldProducts();
}
