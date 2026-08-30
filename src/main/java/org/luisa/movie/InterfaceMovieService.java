package org.luisa.movie;

import java.util.List;

import org.luisa.implementations.InterfaceGenericeEditService;
import org.luisa.movie.dtos.MovieDTORequest;
import org.luisa.movie.dtos.MovieDTOResponse;

public interface InterfaceMovieService extends InterfaceGenericeEditService<MovieDTORequest, MovieDTOResponse> {

    List<MovieDTOResponse> getEntities();

    MovieDTOResponse getById(Long id);

    MovieDTOResponse updateEntity(Long id, MovieDTORequest dto);

    void deleteEntity(Long id);

    List<MovieDTOResponse> findByTitle(String title);

    List<MovieDTOResponse> findByGenre(String genreName);
}
