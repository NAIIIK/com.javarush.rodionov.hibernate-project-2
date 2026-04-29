package com.javarush.rodionov.hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "store",
        indexes = {
                @Index(name = "idx_fk_address_id", columnList = "address_id")
        }
        )
@Getter
@Setter
public class Store {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "store_id")
        private Integer id;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "manager_staff_id", nullable = false, unique = true)
        private Staff manager;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "address_id",  nullable = false)
        private Address address;

        @UpdateTimestamp
        @Column(name = "last_update", nullable = false)
        private LocalDateTime lastUpdate;
}
