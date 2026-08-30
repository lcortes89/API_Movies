package org.luisa.movie.dtos;

import java.util.List;

import org.luisa.actor.dtos.ActorDTOResponse;
import org.luisa.genre.dtos.GenreDTOResponse;
import org.luisa.year.dtos.YearDTOResponse;

public record MovieDTOResponse(
        Long id,
        String title,
        String synopsis,
        YearDTOResponse year,
        List<GenreDTOResponse> genres,
        List<ActorDTOResponse> actors) {
}
