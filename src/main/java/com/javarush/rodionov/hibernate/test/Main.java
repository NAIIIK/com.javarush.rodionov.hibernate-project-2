package com.javarush.rodionov.hibernate.test;

import com.javarush.rodionov.hibernate.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
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

        SessionFactory sessionFactory = cfg.buildSessionFactory();

        sessionFactory.close();
    }
}
