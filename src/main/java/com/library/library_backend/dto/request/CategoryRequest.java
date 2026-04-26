// DTO   Es un objeto que se usa para enviar y recibir datos entre el frontend y el backend
//  DTORequest es  lo que llega del frontend
package com.library.library_backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
        
    private String name;
}
