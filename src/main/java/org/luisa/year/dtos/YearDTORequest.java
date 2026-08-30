package org.luisa.year.dtos;

import jakarta.validation.constraints.NotNull;

public record YearDTORequest(
        @NotNull(message = "Release year cannot be null")
        Integer releaseYear) {
}
