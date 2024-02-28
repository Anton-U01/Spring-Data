package org.example.UniversitySystem;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person{
    @Column(name = "average_grade")
    private double averageGrade;
    @Column
    private int attendance;
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
