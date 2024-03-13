package bg.softuni._productshop.productsShop01.services.impl;


import bg.softuni._productshop.productsShop01.data.repositories.UserRepository;
import bg.softuni._productshop.productsShop01.data.dtos.*;
import bg.softuni._productshop.productsShop01.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
                .map(u -> {
                    UsersWithSoldProductsDto userDto = mapper.map(u, UsersWithSoldProductsDto.class);
                    List<SoldProductsDto> products = u.getSold().stream()
                            .filter(p -> p.getBuyer() != null)
                            .map(p -> mapper.map(p, SoldProductsDto.class)).collect(Collectors.toList());
                    userDto.setSoldProducts(products);
                    return userDto;
                        }
                )
                .toList();
    }

    @Override
    public AllUsersDto getUsersWithAtLeast1SoldProduct() {
        List<UsersWithProductsDto> collect = this.userRepository.findAllUsersWithAtLeast1ItemSold()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(u -> {
                    UsersWithProductsDto userDto = mapper.map(u, UsersWithProductsDto.class);
                    List<ProductImportDto> soldProducts = u.getSold()
                            .stream()
                            .map(product -> mapper.map(product, ProductImportDto.class))
                            .toList();
                    userDto.getSoldProducts().setProducts(soldProducts);
                    userDto.getSoldProducts().setCount(soldProducts.size());
                    return userDto;
                })
                .toList();
        AllUsersDto allUsersDto = new AllUsersDto();
        allUsersDto.setUsersCount(collect.size());
        allUsersDto.setUsers(collect);
        return allUsersDto;
    }
}
