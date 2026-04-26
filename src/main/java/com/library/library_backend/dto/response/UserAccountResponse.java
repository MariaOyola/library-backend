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
public class UserAccountResponse {

    private UUID userId;
    private String name;
    private String email;
    private OffsetDateTime registrationDate;
}
