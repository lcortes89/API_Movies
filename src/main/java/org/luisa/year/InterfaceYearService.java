package org.luisa.year;

import java.util.List;

import org.luisa.implementations.InterfaceGenericeEditService;
import org.luisa.year.dtos.YearDTORequest;
import org.luisa.year.dtos.YearDTOResponse;

public interface InterfaceYearService extends InterfaceGenericeEditService<YearDTORequest, YearDTOResponse> {

    List<YearDTOResponse> getEntities();

    YearDTOResponse getById(Long id);

    YearDTOResponse updateEntity(Long id, YearDTORequest dto);
}
