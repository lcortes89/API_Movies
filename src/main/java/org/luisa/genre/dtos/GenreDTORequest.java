package org.luisa.genre.dtos;

import jakarta.validation.constraints.NotBlank;

public record GenreDTORequest(
        @NotBlank(message = "Name cannot be blank")
        String name) {
}
