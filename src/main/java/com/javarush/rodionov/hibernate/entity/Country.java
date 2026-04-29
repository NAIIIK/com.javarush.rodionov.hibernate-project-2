package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", columnDefinition = "smallint unsigned")
    private Integer id;

    @Column(name = "country", length = 50)
    private String countryName;

    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}
