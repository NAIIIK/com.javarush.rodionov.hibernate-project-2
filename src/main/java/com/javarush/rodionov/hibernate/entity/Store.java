package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
        @Column(name = "store_id", columnDefinition = "tinyint unsigned")
        private Integer id;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "manager_staff_id", nullable = false, unique = true)
        private Staff manager;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "address_id",  nullable = false)
        private Address address;

        @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
        private LocalDateTime lastUpdate;
}
