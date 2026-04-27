package com.library.library_backend.controller;

import com.library.library_backend.dto.request.BookRequest;
import com.library.library_backend.dto.response.BookResponse;
import com.library.library_backend.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // GET /api/books
    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    // GET /api/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    // GET /api/books/available?available=true
    @GetMapping("/available")
    public ResponseEntity<List<BookResponse>> findByAvailable(@RequestParam Boolean available) {
        return ResponseEntity.ok(bookService.findByAvailable(available));
    }

    // GET /api/books/search?title=harry
    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    // POST /api/books
    @PostMapping
    public ResponseEntity<BookResponse> save(@RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(request));
    }

    // PUT /api/books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable UUID id,
                                                @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.update(id, request));
    }

    // DELETE /api/books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}