package com.javarush.rodionov.hibernate.util;

import com.javarush.rodionov.hibernate.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private HibernateUtil() {}

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration cfg = new Configuration();

            cfg.addAnnotatedClass(Actor.class);
            cfg.addAnnotatedClass(Address.class);
            cfg.addAnnotatedClass(Category.class);
            cfg.addAnnotatedClass(City.class);
            cfg.addAnnotatedClass(Country.class);
            cfg.addAnnotatedClass(Customer.class);
            cfg.addAnnotatedClass(Film.class);
            cfg.addAnnotatedClass(FilmText.class);
            cfg.addAnnotatedClass(Inventory.class);
            cfg.addAnnotatedClass(Language.class);
            cfg.addAnnotatedClass(Payment.class);
            cfg.addAnnotatedClass(Rental.class);
            cfg.addAnnotatedClass(Staff.class);
            cfg.addAnnotatedClass(Store.class);

            SESSION_FACTORY = cfg.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Session factory init failed: " + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }
}
