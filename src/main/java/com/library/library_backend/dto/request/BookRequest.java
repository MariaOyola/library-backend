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
public class BookRequest {

     private String title;
    private String author;
    private String isbn;
    private Boolean available;
    private UUID categoryId;  // solo el ID, no el objeto completo

}
