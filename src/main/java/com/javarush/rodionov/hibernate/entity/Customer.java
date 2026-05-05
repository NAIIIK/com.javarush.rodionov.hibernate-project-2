package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "customer",
        indexes = {
                @Index(name = "idx_fk_address_id", columnList = "address_id"),
                @Index(name = "idx_fk_store_id", columnList = "store_id"),
                @Index(name = "idx_last_name", columnList = "last_name")
        }
        )
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", columnDefinition = "smallint unsigned")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id",  nullable = false)
    private Store store;

    @Column(name = "first_name",  length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",  nullable = false)
    private Address address;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "last_update", insertable = false,  updatable = false)
    private LocalDateTime lastUpdate;

    @PrePersist
    public void prePersist() {
        if (active == null) {
            active = true;
        }
    }
}
