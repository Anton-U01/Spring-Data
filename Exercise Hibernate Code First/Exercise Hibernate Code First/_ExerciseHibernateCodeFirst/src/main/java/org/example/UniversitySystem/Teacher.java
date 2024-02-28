package org.example.UniversitySystem;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person{
    @Column
    private String email;
    @Column(name = "salary_per_hour")
    private double salaryPerHour;
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;
}
