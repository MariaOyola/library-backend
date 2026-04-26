package com.library.library_backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    private UUID bookId;   // ID del libro a prestar
    private UUID userId;   // ID del usuario que lo solicita

}
