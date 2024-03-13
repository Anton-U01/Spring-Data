package bg.softuni._productshop.carDealer02.data.data.dtos;

public class SupplierImportDto {
    private String name;
    private boolean isImporter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public SupplierImportDto() {
    }
}
