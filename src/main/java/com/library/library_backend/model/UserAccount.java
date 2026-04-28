package com.library.library_backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.time.OffsetDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;  //anotacion
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_account", schema = "library")
public class UserAccount extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private OffsetDateTime registrationDate;

    @PrePersist
    public void prePersist() {   // la funcionalidad es ejecutar un metodo antes de guardar el objeto en la base de datos. 
        if (registrationDate == null) {
            registrationDate = OffsetDateTime.now();
        }
    }
}
