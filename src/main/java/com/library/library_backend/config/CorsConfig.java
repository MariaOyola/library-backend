package com.library.library_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration le dice a Spring que esta clase tiene configuraciones
// que debe cargar cuando arranca la aplicación
@Configuration
public class CorsConfig {

    // @Bean registra este método como un componente que Spring maneja
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Retornamos una implementación de WebMvcConfigurer
        // que nos permite personalizar el comportamiento de Spring MVC
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // ¿Qué es CORS?
                // Cuando el frontend (React en puerto 5173) le hace una petición
                // al backend (Spring en puerto 8080), el navegador bloquea esa
                // petición por seguridad porque vienen de puertos diferentes.
                // CORS le dice al navegador: "tranquilo, ese origen sí está permitido".

                registry.addMapping("/api/**")
                        // "/api/**" significa que esta configuración aplica
                        // a TODOS los endpoints que empiecen con /api/
                        // Por ejemplo: /api/books, /api/loans, /api/users, etc.

                        .allowedOrigins("http://localhost:5173")
                        // Solo permite peticiones que vengan desde React en localhost:5173
                        // 5173 es el puerto que usa Vite (el que crea proyectos React)
                        // Si tu React corre en otro puerto, cámbialo aquí

                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        // Solo permite estos métodos HTTP:
                        // GET → para consultar datos (findAll, findById)
                        // POST → para crear datos (save)
                        // PUT → para actualizar datos (update)
                        // DELETE → para eliminar datos (delete)

                        .allowedHeaders("*");
                // El "*" significa que acepta cualquier header HTTP
                // que el frontend quiera enviar (Content-Type, Authorization, etc.)
            }
        };
    }
}

// React (5173) → petición HTTP → Backend (8080)
                               //     ↓
                    //          CORS revisa:
                    //     ¿viene de 5173?  → deja pasar
                   //      ¿viene de otro origen?  → bloquea