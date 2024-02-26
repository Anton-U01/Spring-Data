package org.example.composition;

import jakarta.persistence.*;
import org.example.entities.Plane;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(targetEntity = Plane.class, mappedBy = "owner")
    private List<Plane> planes;

    public Company(){
        this.planes = new ArrayList<>();
    }

    public Company(String name){
        this.name = name;
    }
}
