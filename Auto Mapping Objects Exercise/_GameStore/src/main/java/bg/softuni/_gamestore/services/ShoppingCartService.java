package bg.softuni._gamestore.services;

public interface ShoppingCartService {

    String addItem(String title);

    String removeItem(String title);

    String buyItem();
}
