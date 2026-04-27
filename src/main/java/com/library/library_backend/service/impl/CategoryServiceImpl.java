//  implementación de los servicios

package com.library.library_backend.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.library_backend.dto.request.CategoryRequest;
import com.library.library_backend.dto.response.CategoryResponse;
import com.library.library_backend.model.Category;
import com.library.library_backend.repository.ICategoryRepository;
import com.library.library_backend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ICategoryRepository repository;

    @Override
    public List<CategoryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::modelTodto)
                .toList();
    }

    public CategoryResponse findById(UUID id) {
        Category category = repository.findById(id).orElse(null);

        if (category == null) {
            return null;
        }

        return modelTodto(category);
    }

    @Override
    public List<CategoryResponse> findByName(String name) {
        return repository.findByName(name)
                .stream()
                .map(this::modelTodto)
                .toList();
    }

    @Override
    public CategoryResponse save(CategoryRequest C) {
        Category category = dtoToModel(C);
        Category saved = repository.save(category);

        return modelTodto(saved);
    }

    @Override
    public CategoryResponse update(UUID id, CategoryRequest C) {
        Category existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(C.getName());
        Category updated = repository.save(existing);
        return modelTodto(updated);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    // Convertir Model -> DTO Response
    private CategoryResponse modelTodto(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setCategoryId(category.getCategoryId());
        response.setName(category.getName());

        return response;
    }

    // Convertir DTO Request -> Model
    private Category dtoToModel(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        return category;
    }
}