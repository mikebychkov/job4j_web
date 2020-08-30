package com.ch_02_mapping.ex_04_cars_and_owners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {

    private static final StandardServiceRegistry REG = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SF = new MetadataSources(REG).buildMetadata().buildSessionFactory();

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // INIT MODELS

        Engine eng = new Engine("V8 Custom Engine");

        Car car1 = new Car("Ford Mustang");
        car1.setEngine(eng);

        Car car2 = new Car("Jiguli");
        car2.setEngine(eng);

        Driver driver1 = new Driver("Vasiliy Frolov");
        Driver driver2 = new Driver("Feoktet Valeryanov");

        OwnerHistory oh1 = new OwnerHistory(driver1, car1);
        OwnerHistory oh2 = new OwnerHistory(driver2, car2);
        OwnerHistory oh3 = new OwnerHistory(driver1, car2);

        // SYNCHRONIZE MODELS

        car1.addOwnerHistory(oh1);
        car2.addOwnerHistory(oh2);
        car2.addOwnerHistory(oh3);

        driver1.addOwnerHistory(oh1);
        driver1.addOwnerHistory(oh3);
        driver2.addOwnerHistory(oh2);

        // SAVE MODELS

        Session session = SF.openSession();
        session.beginTransaction();
        session.save(eng);
        session.save(car1);
        session.save(car2);
        session.save(driver1);
        session.save(driver2);
        session.getTransaction().commit();
        session.close();

        // READ MODELS

        session = SF.openSession();
        session.beginTransaction();

        List<Car> carList = session.createQuery("FROM com.ch_02_mapping.ex_04_cars_and_owners.Car").list();
        carList.forEach(c -> {
            System.out.printf("%s CAR: %s, ENGINE %s%n", "=".repeat(50), c.getName(), c.getEngine().getName());
            c.getOwnerHistory().forEach(oh -> System.out.printf("%s DRIVER: %s%n", "=".repeat(50), oh.getDriver().getName()));
        });

        session.getTransaction().commit();
        session.close();
    }
}
