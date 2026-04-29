package com.javarush.rodionov.hibernate.dao;

import com.javarush.rodionov.hibernate.entity.*;
import com.javarush.rodionov.hibernate.util.HibernateUtil;
import com.javarush.rodionov.hibernate.util.QueryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalDao {

    private final SessionFactory sessionFactory;

    public RentalDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Rental returnFilm(Integer rentalId) {
        return sessionFactory.fromTransaction(session -> {

            Rental rental = session.find(Rental.class, rentalId);

            if (rental == null) {
                throw new IllegalArgumentException("Rental with id " + rentalId + " not found");
            }

            if (rental.getReturnDate() != null) {
                throw new IllegalStateException("Rental with id " + rentalId + " is already returned");
            }

            rental.setReturnDate(LocalDateTime.now());

            return rental;
        });
    }

    public Payment rentFilm(Integer customerId,
                            Integer filmId,
                            Integer storeId,
                            Integer staffId,
                            BigDecimal amount) {

        return sessionFactory.fromTransaction(session -> {

            Inventory inventory = session.createQuery(QueryUtil.RENT_FILM_QUERY, Inventory.class)
                    .setParameter("storeId", storeId)
                    .setParameter("filmId", filmId)
                    .setMaxResults(1)
                    .uniqueResult();

            if (inventory == null) {
                throw new IllegalStateException(
                        "Inventory for film id " + filmId +
                                " in the store with id " + storeId + "not found")
                        ;
            }

            Customer customer = session.getReference(Customer.class, customerId);
            Staff staff = session.getReference(Staff.class, staffId);

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setStaff(staff);
            rental.setReturnDate(null);

            session.persist(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(staff);
            payment.setRental(rental);
            payment.setAmount(amount);
            payment.setPaymentDate(LocalDateTime.now());

            session.persist(payment);

            return payment;
        });
    }
}
