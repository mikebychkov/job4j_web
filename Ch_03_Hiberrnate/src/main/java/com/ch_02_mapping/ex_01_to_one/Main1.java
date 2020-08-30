package com.ch_02_mapping.ex_01_to_one;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main1 {

    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final Logger logger = LogManager.getLogger(Main1.class);

    public static void main(String[] args) {

        CarType1 type = new CarType1("Mazda");
        Car1 car = new Car1("Mazda RX7");
        car.setType(type);

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(type);
        session.save(car);
        session.getTransaction().commit();
        session.close();

        session = sf.openSession();
        session.beginTransaction();

        List<Car1> list = session.createQuery("FROM com.ch_02_mapping.ex_01_to_one.Car1").list();
        list.forEach(c -> System.out.printf("car: %s, type: %s%n", c.getName(), c.getType().getName()));

        session.getTransaction().commit();
        session.close();
    }
}
