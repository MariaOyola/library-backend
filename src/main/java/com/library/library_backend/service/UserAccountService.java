package com.library.library_backend.service;

import java.util.List;
import java.util.UUID;

import com.library.library_backend.dto.request.UserAccountRequest;
import com.library.library_backend.dto.response.UserAccountResponse;

public interface UserAccountService {

    List<UserAccountResponse>findAll();
    UserAccountResponse findById(UUID id);
    UserAccountResponse save(UserAccountRequest U); 
    List<UserAccountResponse>findByEmail(String email);
    UserAccountResponse update(UUID id, UserAccountRequest U); 
    UserAccountResponse response(UserAccountRequest U); 
    void delete(UUID id); 

}
