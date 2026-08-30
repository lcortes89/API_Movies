package org.luisa.genre;

import java.util.List;

import org.luisa.genre.dtos.GenreDTOResponse;

public interface InterfaceGenreService {

    List<GenreDTOResponse> getEntities();

    GenreDTOResponse getById(Long id);
}
