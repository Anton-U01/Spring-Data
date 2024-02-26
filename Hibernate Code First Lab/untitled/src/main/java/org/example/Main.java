package org.example;

import org.example.composition.PlateNumber;
import org.example.entities.Bike;
import org.example.entities.Car;
import org.example.entities.Plane;
import org.example.entities.Vehicle;
import org.hibernate.boot.jaxb.hbm.internal.CacheAccessTypeConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("general");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        PlateNumber plateNumber = new PlateNumber("PB2500MT");
        Vehicle car = new Car("BMW",new BigDecimal(5),"GAS",5,plateNumber);
        Vehicle bike = new Bike("bmx",new BigDecimal(1),"NULL");
        Vehicle plane = new Plane("Boeing",new BigDecimal(20),"Petrol",150);

        entityManager.persist(plateNumber);
        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}