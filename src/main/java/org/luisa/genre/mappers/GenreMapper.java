package org.luisa.genre.mappers;

import org.luisa.genre.GenreEntity;
import org.luisa.genre.dtos.GenreDTORequest;
import org.luisa.genre.dtos.GenreDTOResponse;

public class GenreMapper {

    private GenreMapper() {
    }

    public static GenreDTOResponse toDTO(GenreEntity entity) {
        return new GenreDTOResponse(entity.getId(), entity.getName());
    }

    public static GenreEntity toEntity(GenreDTORequest dtoRequest) {
        GenreEntity genre = new GenreEntity();
        genre.setName(dtoRequest.name());
        return genre;
    }
}
