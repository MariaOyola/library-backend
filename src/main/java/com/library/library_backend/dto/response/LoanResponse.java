package com.library.library_backend.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {

    private UUID loanId;
    private String bookTitle;     // nombre del libro
    private String bookAuthor;    // autor del libro
    private String userName;      // nombre del usuario
    private String userEmail;     // email del usuario
    private OffsetDateTime loanDate;
    private OffsetDateTime returnDate;
    private String status;        // "ACTIVE" o "RETURNED" — calculado
                                  
}
