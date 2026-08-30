package org.luisa.movie;

import java.util.List;

import org.luisa.movie.dtos.MovieDTOResponse;

public interface InterfaceMovieService {

    List<MovieDTOResponse> getEntities();

    MovieDTOResponse getById(Long id);
}
