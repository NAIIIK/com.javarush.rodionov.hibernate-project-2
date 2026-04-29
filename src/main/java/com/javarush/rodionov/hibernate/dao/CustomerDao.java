package com.javarush.rodionov.hibernate.dao;

import com.javarush.rodionov.hibernate.entity.Address;
import com.javarush.rodionov.hibernate.entity.Customer;
import com.javarush.rodionov.hibernate.entity.Store;
import com.javarush.rodionov.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CustomerDao {

    private final SessionFactory sessionFactory;

    public CustomerDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Customer createCustomer(String firstName,
                               String lastName,
                               String email,
                               Integer storeId,
                               Integer addressId) {

        return sessionFactory.fromTransaction(session -> {

            Store store = session.getReference(Store.class, storeId);
            Address address = session.getReference(Address.class, addressId);

            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setStore(store);

            session.persist(customer);

            return customer;
        });
    }
}
