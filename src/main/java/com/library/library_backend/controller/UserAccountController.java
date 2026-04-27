package com.library.library_backend.controller;

import com.library.library_backend.dto.request.UserAccountRequest;
import com.library.library_backend.dto.response.UserAccountResponse;
import com.library.library_backend.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    // GET /api/users
    @GetMapping
    public ResponseEntity<List<UserAccountResponse>> findAll() {
        return ResponseEntity.ok(userAccountService.findAll());
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userAccountService.findById(id));
    }

    // GET /api/users/search?email=maria
    @GetMapping("/search")
    public ResponseEntity<List<UserAccountResponse>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userAccountService.findByEmail(email));
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<UserAccountResponse> save(@RequestBody UserAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userAccountService.save(request));
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserAccountResponse> update(@PathVariable UUID id,
                                                       @RequestBody UserAccountRequest request) {
        return ResponseEntity.ok(userAccountService.update(id, request));
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}