package org.example.HospitalDatabase;

import jakarta.persistence.*;
import jdk.jshell.Diag;
import org.example.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {
    @Column
    private LocalDate date;
    @Column
    private String comments;
    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "diagnose_id",referencedColumnName = "id")
    private Diagnose diagnose;
}
