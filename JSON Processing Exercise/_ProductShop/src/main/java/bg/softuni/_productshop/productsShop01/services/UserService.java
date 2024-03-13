package bg.softuni._productshop.productsShop01.services;

import bg.softuni._productshop.productsShop01.data.dtos.AllUsersDto;
import bg.softuni._productshop.productsShop01.data.dtos.UsersWithSoldProductsDto;

import java.util.List;

public interface UserService {
    List<UsersWithSoldProductsDto> getUsersWithSoldProducts();
    AllUsersDto getUsersWithAtLeast1SoldProduct();
}
