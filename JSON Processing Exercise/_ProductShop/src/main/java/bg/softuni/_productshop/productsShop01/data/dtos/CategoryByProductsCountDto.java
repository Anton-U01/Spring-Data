package bg.softuni._productshop.productsShop01.data.dtos;

import java.math.BigDecimal;

public class CategoryByProductsCountDto {
    private String category;
    private int productsCount;
    private double averagePrice;
    private BigDecimal totalRevenue;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public CategoryByProductsCountDto() {
    }
}
