package bg.softuni._productshop.productsShop01.data.dtos;

import bg.softuni._productshop.productsShop01.data.models.User;

import java.util.Set;

public class UserDto {
    private String firstName;
    private String lastName;
    private int age;

    private Set<User> friends;

    public String getFullName(){
        return this.firstName + " " + this.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public UserDto() {
    }

}
