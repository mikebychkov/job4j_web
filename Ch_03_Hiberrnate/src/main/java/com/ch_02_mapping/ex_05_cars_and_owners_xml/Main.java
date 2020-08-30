package com.ch_02_mapping.ex_05_cars_and_owners_xml;

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

        EngineXML eng = new EngineXML("V8 Custom Engine");

        CarXML car1 = new CarXML("Ford Mustang");
        car1.setEngine(eng);

        CarXML car2 = new CarXML("Jiguli");
        car2.setEngine(eng);

        DriverXML driver1 = new DriverXML("Vasiliy Frolov");
        DriverXML driver2 = new DriverXML("Feoktet Valeryanov");

        OwnerHistoryXML oh1 = new OwnerHistoryXML(driver1, car1);
        OwnerHistoryXML oh2 = new OwnerHistoryXML(driver2, car2);
        OwnerHistoryXML oh3 = new OwnerHistoryXML(driver1, car2);

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

        List<CarXML> carList = session.createQuery("FROM com.ch_02_mapping.ex_05_cars_and_owners_xml.CarXML").list();
        carList.forEach(c -> {
            System.out.printf("%s CAR: %s, ENGINE %s%n", "=".repeat(50), c.getName(), c.getEngine().getName());
            c.getOwnerHistory().forEach(oh -> System.out.printf("%s DRIVER: %s%n", "=".repeat(50), oh.getDriver().getName()));
        });

        session.getTransaction().commit();
        session.close();
    }
}
