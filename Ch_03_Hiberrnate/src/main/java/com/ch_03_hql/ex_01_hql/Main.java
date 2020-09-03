package com.ch_03_hql.ex_01_hql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    private static final StandardServiceRegistry REG = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SF = new MetadataSources(REG).buildMetadata().buildSessionFactory();

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Candidate c1 = new Candidate("Nikolas", 5, 5000);
        Candidate c2 = new Candidate("Philemon", 1, 1000);
        Candidate c3 = new Candidate("Petra", 2, 2000);

        // SAVE MODELS ----------------------------------------------------------------------------

        // NEW SESSION ----------
        Session session = SF.openSession();
        session.beginTransaction();

        session.save(c1);
        session.save(c2);
        session.save(c3);

        session.getTransaction().commit();
        session.close();

        // READ MODELS ----------------------------------------------------------------------------

        // NEW SESSION ----------
        session = SF.openSession();
        session.beginTransaction();

        // ALL
        List<Candidate> listAll = session.createQuery("FROM com.ch_03_hql.ex_01_hql.Candidate").list();
        listAll.forEach(c -> LOGGER.info("=".repeat(100) + c));

        // BY ID
        Query<Candidate> q1 = session.createQuery("FROM com.ch_03_hql.ex_01_hql.Candidate WHERE id = :id");
        q1.setParameter("id", 1);
        q1.list().forEach(c -> LOGGER.info("=".repeat(100) + c));

        // BY NAME
        Query<Candidate> q2 = session.createQuery("FROM com.ch_03_hql.ex_01_hql.Candidate WHERE name = :name");
        q2.setParameter("name", "Petra");
        q2.list().forEach(c -> LOGGER.info("=".repeat(100) + c));

        session.getTransaction().commit();
        session.close();

        // NEW SESSION ----------
        session = SF.openSession();
        session.beginTransaction();

        // UPDATE
        Query<Candidate> q3 = session.createQuery("UPDATE com.ch_03_hql.ex_01_hql.Candidate C SET C.salary = :s WHERE id = :id");
        q3.setParameter("id", 1);
        q3.setParameter("s", (double) 10000);
        q3.executeUpdate();

        // DELETE
        Query<Candidate> q4 = session.createQuery("DELETE com.ch_03_hql.ex_01_hql.Candidate WHERE id = :id");
        q4.setParameter("id", 2);
        q4.executeUpdate();

        session.getTransaction().commit();
        session.close();

        // NEW SESSION ----------
        session = SF.openSession();
        session.beginTransaction();

        // ALL
        listAll = session.createQuery("FROM com.ch_03_hql.ex_01_hql.Candidate").list();
        listAll.forEach(c -> LOGGER.info("=".repeat(100) + c));

        session.getTransaction().commit();
        session.close();
    }
}
