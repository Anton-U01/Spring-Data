package bg.softuni._xmlexercise.carDealer.data.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier",fetch = FetchType.EAGER)
    private List<Part> parts;

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

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public Supplier() {
    }

    public Supplier(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }
}
