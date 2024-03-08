package bg.softuni._gamestore.services;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class GameDto {
    @Pattern(regexp = "[A-Z]+\\w+",message = "Title should start with a capital letter.")
    @Size(min = 3,max = 100, message = "Length should be between 3 and 100 letters.")
    private String title;
    @Min(value = 0)
    private double price;
    @Min(value = 0)
    private double size;
    @Size(min = 11,max = 11)
    private String trailer;
    @Pattern(regexp = "^(?:http//)*(?:https://)*.+",message = "Thumbnail does not match the correct format")
    private String thumbnail;
    @Size(min = 20)
    private String description;
    private LocalDate releaseDate;

    public GameDto(String title, double price, double size, String trailer, String thumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public GameDto() {
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
