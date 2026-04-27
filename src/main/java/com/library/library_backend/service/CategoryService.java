package com.library.library_backend.service;

import com.library.library_backend.dto.request.CategoryRequest;
import com.library.library_backend.dto.response.CategoryResponse;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
     List<CategoryResponse> findAll();
    // JpaRepository<Category, UUID>
    // Category → el modelo que maneja
    // UUID     → el tipo del ID
    List<CategoryResponse>findByName(String name);
    CategoryResponse findById(UUID Id); 
    CategoryResponse save(CategoryRequest C); 
    CategoryResponse update(UUID id, CategoryRequest C);
    void delete(UUID id); 
}
