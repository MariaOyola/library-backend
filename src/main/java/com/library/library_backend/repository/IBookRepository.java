package com.library.library_backend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.library_backend.model.Book;

@Repository
public interface IBookRepository extends JpaRepository <Book, UUID> {

    List<Book> findByBookId(UUID bookId);
    List<Book> findByAvailable(Boolean available);// Buscar todos los libros disponibles 
    List<Book> findByTitle(String title);
    
}
