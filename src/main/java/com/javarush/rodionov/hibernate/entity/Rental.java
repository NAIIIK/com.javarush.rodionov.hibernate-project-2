package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "rental",
        indexes = {
                @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
                @Index(name = "idx_fk_inventory_id", columnList = "inventory_id"),
                @Index(name = "idx_fk_staff_id", columnList = "staff_id")
        },
        uniqueConstraints = @UniqueConstraint(
                name = "rental_date",
                columnNames = {"rental_date", "inventory_id", "customer_id"}
        )
)
@Getter
@Setter
public class Rental {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "rental_id")
        private Integer id;

        @Column(name = "rental_date", nullable = false)
        private LocalDateTime rentalDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "inventory_id", nullable = false)
        private Inventory inventory;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @Column(name = "return_date")
        private LocalDateTime returnDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "staff_id", nullable = false)
        private Staff staff;

        @Column(name = "last_update", insertable = false, updatable = false)
        private LocalDateTime lastUpdate;
}
