package org.luisa.movie.mappers;

import org.luisa.actor.mappers.ActorMapper;
import org.luisa.genre.mappers.GenreMapper;
import org.luisa.movie.MovieEntity;
import org.luisa.movie.dtos.MovieDTOResponse;
import org.luisa.year.mappers.YearMapper;

public class MovieMapper {

    private MovieMapper() {
    }

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        return new MovieDTOResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getSynopsis(),
                entity.getYear() != null ? YearMapper.toDTO(entity.getYear()) : null,
                entity.getGenres().stream().map(GenreMapper::toDTO).toList(),
                entity.getActors().stream().map(ActorMapper::toDTO).toList()
        );
    }
}
