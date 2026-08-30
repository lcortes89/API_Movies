package org.luisa.year;

import java.util.List;

import org.luisa.year.dtos.YearDTOResponse;

public interface InterfaceYearService {

    List<YearDTOResponse> getEntities();

    YearDTOResponse getById(Long id);
}
