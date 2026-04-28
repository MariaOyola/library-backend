package com.library.library_backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.library.library_backend.dto.request.UserAccountRequest;
import com.library.library_backend.dto.response.UserAccountResponse;
import com.library.library_backend.model.UserAccount;
import com.library.library_backend.repository.IUserAccountRepository;
import com.library.library_backend.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final IUserAccountRepository repository;

    @Override
    public List<UserAccountResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::modelToDto)
                .toList();
    }

    @Override
    public UserAccountResponse findById(UUID id) {
        UserAccount user = repository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        return modelToDto(user);
    }

    @Override
    public List<UserAccountResponse> findByEmail(String email) {
        return repository.findByEmail(email)
                .stream()
                .map(this::modelToDto)
                .toList();
    }

    @Override
    public UserAccountResponse save(UserAccountRequest u) {
        UserAccount user = dtoToModel(u);
        UserAccount saved = repository.save(user);

        return modelToDto(saved);
    }

    @Override
    public UserAccountResponse update(UUID id, UserAccountRequest u) {
        UserAccount existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(u.getName());
        existing.setEmail(u.getEmail());

        UserAccount updated = repository.save(existing);

        return modelToDto(updated);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public UserAccountResponse response(UserAccountRequest request) {
        return save(request);
    }

    //  Model → DTO
    private UserAccountResponse modelToDto(UserAccount user) {
        UserAccountResponse response = new UserAccountResponse();
        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRegistrationDate(user.getRegistrationDate());
        return response;
    }

    //  DTO → Model
    private UserAccount dtoToModel(UserAccountRequest request) {
        UserAccount user = new UserAccount();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRegistrationDate(OffsetDateTime.now());

        return user;
    }
}