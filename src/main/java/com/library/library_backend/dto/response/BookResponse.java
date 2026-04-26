package com.library.library_backend.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private UUID bookId;
    private String title;
    private String author;
    private String isbn;
    private Boolean available;
    private String categoryName;  // solo el nombre, no todo el objeto Category

}
