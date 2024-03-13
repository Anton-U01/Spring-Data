package bg.softuni._productshop.productsShop01.data.dtos;

import java.util.List;

public class AllUsersDto {
    private int usersCount;
    private List<UsersWithProductsDto> users;

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UsersWithProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UsersWithProductsDto> users) {
        this.users = users;
    }

    public AllUsersDto() {
    }
}
