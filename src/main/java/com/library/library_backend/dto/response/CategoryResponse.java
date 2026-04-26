//lo que el backend le responde al frontend
// lo que se le responde al frontend
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
public class CategoryResponse {

    private UUID categoryId;
    private String name;
}
