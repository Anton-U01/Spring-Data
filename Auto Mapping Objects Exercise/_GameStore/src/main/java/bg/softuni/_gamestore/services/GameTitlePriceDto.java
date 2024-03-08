package bg.softuni._gamestore.services;

public class GameTitlePriceDto {
    private String title;
    private double price;

    public GameTitlePriceDto() {
    }

    public GameTitlePriceDto(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
