package com.ch_02_mapping.ex_03_to_many_bidirectional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main3 {

    private static final StandardServiceRegistry REG = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SF = new MetadataSources(REG).buildMetadata().buildSessionFactory();

    private static final Logger LOGGER = LogManager.getLogger(Main3.class);

    public static void main(String[] args) {

        CarType3 type = new CarType3("Mazda");
        Car3 car = new Car3("Mazda RX7");
        Car3 car2 = new Car3("Mazda RX8");

        car.setType(type);
        car2.setType(type);

        type.addCar(car);
        type.addCar(car2);

        Session session = SF.openSession();
        session.beginTransaction();
        session.save(type);
        session.getTransaction().commit();
        session.close();

        session = SF.openSession();
        session.beginTransaction();

        System.out.println("=".repeat(50));
        List<Car3> list1 = session.createQuery("FROM com.ch_02_mapping.ex_03_to_many_bidirectional.Car3").list();
        list1.forEach(c -> System.out.printf("car: %s, type: %s%n", c.getName(), c.getType().getName()));

        System.out.println("=".repeat(50));
        List<CarType3> list2 = session.createQuery("FROM com.ch_02_mapping.ex_03_to_many_bidirectional.CarType3").list();
        list2.forEach(ct -> {
            System.out.printf("type: %s%n", ct.getName());
            ct.getCars().forEach(c -> System.out.printf("car: %s%n", c.getName()));
        });

        session.getTransaction().commit();
        session.close();
    }
}
