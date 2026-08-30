package org.luisa.movie.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDTORequest(
        @NotBlank(message = "Title cannot be blank")
        String title,

        String synopsis,

        @NotNull(message = "Year id cannot be null")
        Long yearId,

        @NotNull(message = "Genre id cannot be null")
        Long genreId,

        List<Long> actorIds) {
}
