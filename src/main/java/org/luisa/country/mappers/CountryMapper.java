package org.luisa.country.mappers;

import org.luisa.country.CountryEntity;
import org.luisa.country.dtos.CountryDTORequest;
import org.luisa.country.dtos.CountryDTOResponse;

// @Component - Si quisieramos un bean en el contenedor de spring
public class CountryMapper {
    
    public static CountryEntity toEntity(CountryDTORequest dtoRequest) {
        CountryEntity country = new CountryEntity();
        country.setName(dtoRequest.name());
        return country;
    }

    public static CountryDTOResponse toDTO(CountryEntity entity) {
        CountryDTOResponse dtoResponse = new CountryDTOResponse(entity.getId(), entity.getName());
        return dtoResponse;
    }

}
