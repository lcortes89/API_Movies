package org.factoriaf5.country.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * CountryDTORequest
 * @param name
 * 
 * '@NotBlank' '@NotNull'
 * Spring lanza automáticamente `MethodArgumentNotValidException` → **Spring Boot ya devuelve un `500 Internal Server Error` por defecto**, pero con un cuerpo de error genérico y poco amigable.
 */
public record CountryDTORequest(
    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    String name) {
    
}
