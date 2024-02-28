package org.example.UniversitySystem;

import jakarta.persistence.*;
import org.example.BaseEntity;

@MappedSuperclass
public abstract class Person extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
}
