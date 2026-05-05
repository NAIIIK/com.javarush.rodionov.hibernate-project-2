package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "inventory",
        indexes = {
                @Index(name = "idx_fk_film_id", columnList = "film_id"),
                @Index(name = "idx_store_id_film_id", columnList = "store_id, film_id")
        }
)
@Getter
@Setter
public class Inventory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "inventory_id")
        private Integer id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "film_id", nullable = false)
        private Film film;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "store_id", nullable = false)
        private Store store;

        @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
        private LocalDateTime lastUpdate;
}
