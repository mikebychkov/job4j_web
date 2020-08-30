package com.ch_02_mapping.ex_02_to_many;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main2 {

    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final Logger logger = LogManager.getLogger(Main2.class);

    public static void main(String[] args) {

        CarType2 type = new CarType2("Mazda");
        Car2 car = new Car2("Mazda RX7");
        Car2 car2 = new Car2("Mazda RX8");

        type.addCar(car);
        type.addCar(car2);

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(type);
        session.getTransaction().commit();
        session.close();

        session = sf.openSession();
        session.beginTransaction();

        List<CarType2> list = session.createQuery("FROM com.ch_02_mapping.ex_02_to_many.CarType2").list();
        list.forEach(ct -> {
            System.out.printf("type: %s%n", ct.getName());
            ct.getCars().forEach(c -> System.out.printf("car: %s%n", c.getName()));
        });

        session.getTransaction().commit();
        session.close();
    }
}
