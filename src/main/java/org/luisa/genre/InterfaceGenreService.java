package org.luisa.genre;

import java.util.List;

import org.luisa.genre.dtos.GenreDTORequest;
import org.luisa.genre.dtos.GenreDTOResponse;
import org.luisa.implementations.InterfaceGenericeEditService;

public interface InterfaceGenreService extends InterfaceGenericeEditService<GenreDTORequest, GenreDTOResponse> {

    List<GenreDTOResponse> getEntities();

    GenreDTOResponse getById(Long id);

    GenreDTOResponse updateEntity(Long id, GenreDTORequest dto);
}
