//  Service  recibe los datos del controller, aplica la lógica del negocio y llama al repository. 
//  la interfaz (qué hace)
package com.library.library_backend.service;

import com.library.library_backend.dto.request.CategoryRequest;
import com.library.library_backend.dto.response.CategoryResponse;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryResponse> findAll();
    CategoryResponse findById(UUID id);
    CategoryResponse save(CategoryRequest C); 
    List<CategoryResponse> findByName(String name); 
    CategoryResponse update(UUID id, CategoryRequest C);
    void delete(UUID id);
}