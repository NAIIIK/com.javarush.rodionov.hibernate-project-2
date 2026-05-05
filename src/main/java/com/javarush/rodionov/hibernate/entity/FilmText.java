package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "film_text")
@Getter
@Setter
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "film_id", columnDefinition = "smallint")
    private Film film;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
}
