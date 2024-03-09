package bg.softuni._productshop.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column
    @Size(min = 3,max = 15)
    private String name;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
