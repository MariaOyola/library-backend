package com.library.library_backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.library.library_backend.dto.request.BookRequest;
import com.library.library_backend.dto.response.BookResponse;
import com.library.library_backend.model.Book;
import com.library.library_backend.model.Category;
import com.library.library_backend.repository.IBookRepository;
import com.library.library_backend.repository.ICategoryRepository;
import com.library.library_backend.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final IBookRepository repository;
    private final ICategoryRepository categoryRepository; 

    @Override
    public List<BookResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::modelToDto)
                .toList();
    }

    @Override
    public BookResponse findById(UUID id) {
        Book book = repository.findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        return modelToDto(book);
    }

    @Override
    public List<BookResponse> findByAvailable(Boolean available) {
        return repository.findByAvailable(available)
                .stream()
                .map(this::modelToDto)
                .toList();
    }

    @Override
    public List<BookResponse> findByTitle(String title) {
        return repository.findByTitle(title)
                .stream()
                .map(this::modelToDto)
                .toList();
    }

    @Override
    public BookResponse save(BookRequest B) {
        Book book = dtoToModel(B);
        Book saved = repository.save(book);

        return modelToDto(saved);
    }

    @Override
    public BookResponse update(UUID id, BookRequest B) {
        Book existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setTitle(B.getTitle());
        existing.setAuthor(B.getAuthor());
        existing.setAvailable(B.getAvailable());

        Book updated = repository.save(existing);

        return modelToDto(updated);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    //  Model → DTO
    private BookResponse modelToDto(Book book) {
        BookResponse response = new BookResponse();
        response.setBookId(book.getBookId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setAvailable(book.getAvailable());
        if (book.getCategory()!= null) {
           response.setCategoryName(book.getCategory().getName());
    }
    return response;
    }

    //  DTO → Model
  private Book dtoToModel(BookRequest request) {
    Book book = new Book();
    book.setTitle(request.getTitle());
    book.setAuthor(request.getAuthor());
    book.setIsbn(request.getIsbn());          // ← agrega esto
    book.setAvailable(true);

    // buscar y setear la categoría
    if (request.getCategoryId() != null) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);            // ← agrega esto
    }
    return book;
}
}


