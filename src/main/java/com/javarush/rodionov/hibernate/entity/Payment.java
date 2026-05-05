package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "payment",
        indexes = {
                @Index(name = "idx_fk_payment_rental", columnList = "rental_id"),
                @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
                @Index(name = "idx_fk_staff_id", columnList = "staff_id")
        }
)
@Getter
@Setter
public class Payment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "payment_id", columnDefinition = "smallint unsigned")
        private Integer id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "staff_id",  nullable = false)
        private Staff staff;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "rental_id")
        private Rental rental;

        @Column(name = "amount", precision = 5, scale = 2, nullable = false)
        private BigDecimal amount;

        @Column(name = "payment_date", nullable = false)
        private LocalDateTime paymentDate;

        @Column(name = "last_update",  insertable = false, updatable = false)
        private LocalDateTime lastUpdate;
}
