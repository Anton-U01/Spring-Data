package com.example.data.base;

import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
