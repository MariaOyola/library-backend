package com.library.library_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "library")
public class Category extends BaseModel {  // hereda created_at y updated_at
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // UUID generado automáticamente
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

}