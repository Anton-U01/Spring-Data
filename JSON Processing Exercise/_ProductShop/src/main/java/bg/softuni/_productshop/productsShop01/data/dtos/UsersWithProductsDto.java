package bg.softuni._productshop.productsShop01.data.dtos;

public class UsersWithProductsDto {
    private String firstName;
    private String lastName;
    private int age;


    private SoldProductDto soldProducts;
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


    public SoldProductDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductDto soldProducts) {
        this.soldProducts = soldProducts;
    }
    public UsersWithProductsDto() {
        soldProducts = new SoldProductDto();
    }
}
