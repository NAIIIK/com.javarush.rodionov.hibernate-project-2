package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "city",
        indexes = {
                @Index(name = "idx_fk_country_id", columnList = "country_id")
        }
        )
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", columnDefinition = "smallint unsigned")
    private Integer id;

    @Column(name = "city",  length = 50)
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}
