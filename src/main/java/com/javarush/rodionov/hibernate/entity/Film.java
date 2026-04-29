package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(
        name = "film",
        indexes = {
                @Index(name = "idx_fk_language_id", columnList = "language_id"),
                @Index(name = "idx_fk_original_language_id", columnList = "original_language_id"),
                @Index(name = "idx_title", columnList = "title")
        }
)
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "smallint unsigned")
    private Integer id;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Integer releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "tinyint unsigned")
    private Integer rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2, nullable = false)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", precision = 5,  scale = 2, nullable = false)
    private BigDecimal replacementCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;

    @Column(name = "special_features")
    private String special_features;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}
