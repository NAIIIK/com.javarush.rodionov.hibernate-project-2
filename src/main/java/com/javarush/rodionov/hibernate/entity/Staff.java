package com.javarush.rodionov.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "staff",
        indexes = {
                @Index(name = "idx_fk_address_id", columnList = "address_id"),
                @Index(name = "idx_fk_store_id", columnList = "store_id")
        }
        )
@Getter
@Setter
public class Staff {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "staff_id", columnDefinition = "tinyint unsigned")
        private Integer id;

        @Column(name = "first_name", length = 45, nullable = false)
        private String firstName;

        @Column(name = "last_name", length = 45, nullable = false)
        private String lastName;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "address_id", nullable = false)
        private Address address;

        @Lob
        @Column(columnDefinition = "blob")
        private byte[] picture;

        @Column(name = "email",  length = 50)
        private String email;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "store_id", nullable = false)
        private Store store;

        @Column(name = "active", nullable = false)
        private Boolean active;

        @Column(name = "username", length = 16, nullable = false)
        private String username;

        @Column(name = "password", length = 40)
        private String password;

        @Column(name = "last_update", insertable = false, updatable = false)
        private LocalDateTime lastUpdate;

        @PrePersist
        private void prePersist() {
                if (active == null) {
                        active = true;
                }
        }
}
