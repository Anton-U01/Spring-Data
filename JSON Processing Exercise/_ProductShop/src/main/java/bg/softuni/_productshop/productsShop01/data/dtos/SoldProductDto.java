package bg.softuni._productshop.productsShop01.data.dtos;

import java.util.List;

public class SoldProductDto {
    private int count;
    private List<ProductImportDto> products;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductImportDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductImportDto> products) {
        this.products = products;
    }

    public SoldProductDto() {
    }
}
