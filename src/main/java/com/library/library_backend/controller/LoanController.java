package com.library.library_backend.controller;

import com.library.library_backend.dto.request.LoanRequest;
import com.library.library_backend.dto.response.LoanResponse;
import com.library.library_backend.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    // GET /api/loans
    @GetMapping
    public ResponseEntity<List<LoanResponse>> findAll() {
        return ResponseEntity.ok(loanService.findAll());
    }

    // GET /api/loans/user/{id}
    @GetMapping("/user/{id}")
    public ResponseEntity<List<LoanResponse>> findByUser(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.findByUser(id));
    }

    // POST /api/loans
    @PostMapping
    public ResponseEntity<LoanResponse> save(@RequestBody LoanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.save(request));
    }

    // POST /api/loans/register
    @PostMapping("/register")
    public ResponseEntity<LoanResponse> registerLoan(@RequestBody LoanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.registerLoan(request));
    }

    // PUT /api/loans/{id}
    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> update(@PathVariable UUID id,
                                                @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.update(id, request));
    }

    // PUT /api/loans/{id}/return
    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponse> returnBook(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }

    // DELETE /api/loans/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}