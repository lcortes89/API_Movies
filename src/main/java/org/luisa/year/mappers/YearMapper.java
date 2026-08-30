package org.luisa.year.mappers;

import org.luisa.year.YearEntity;
import org.luisa.year.dtos.YearDTORequest;
import org.luisa.year.dtos.YearDTOResponse;

public class YearMapper {

    private YearMapper() {
    }

    public static YearDTOResponse toDTO(YearEntity entity) {
        return new YearDTOResponse(entity.getId(), entity.getReleaseYear());
    }

    public static YearEntity toEntity(YearDTORequest dtoRequest) {
        YearEntity year = new YearEntity();
        year.setReleaseYear(dtoRequest.releaseYear());
        return year;
    }
}
