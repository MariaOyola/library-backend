package com.library.library_backend.service;

import java.util.List;
import java.util.UUID;

import com.library.library_backend.dto.request.BookRequest;
import com.library.library_backend.dto.response.BookResponse;

public interface BookService {

    List<BookResponse>findAll();
    BookResponse findById(UUID id);
    BookResponse save(BookRequest B); 
    List<BookResponse>findByAvailable(Boolean available); 
    List<BookResponse>findByTitle(String title); 
    BookResponse update(UUID id, BookRequest B); 
    void delete(UUID id);  
}
