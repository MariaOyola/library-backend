// Repository  Es la capa que habla directamente con la base de datos.
// JpaRepository que ya trae los métodos básicos listos — findAll(), findById(), save(), delete()

package com.library.library_backend.repository;

import java.util.List;
import java.util.UUID;
import com.library.library_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository <Category, UUID> {
                                                           // Category = el modelo que maneja
       
List<Category> findByCategoryId(UUID categoryId);                                                           // // Category = el modelo que maneja
List<Category> findByName(String name);
                                          
}
