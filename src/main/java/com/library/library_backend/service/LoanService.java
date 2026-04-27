package com.library.library_backend.service;

import java.util.List;
import java.util.UUID;

import com.library.library_backend.dto.request.LoanRequest;
import com.library.library_backend.dto.response.LoanResponse;

public interface LoanService {
    
    List<LoanResponse> findAll();
    List<LoanResponse> findByUser(UUID id);  // historial de un usuario
    LoanResponse registerLoan(LoanRequest L);  // registrar préstamo
    LoanResponse save(LoanRequest L); 
    LoanResponse update(UUID id, LoanRequest L);
    LoanResponse returnBook(UUID loanId); 
    void delete(UUID id);    

}