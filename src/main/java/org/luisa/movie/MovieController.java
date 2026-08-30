package org.luisa.movie;

import java.util.List;

import org.luisa.movie.dtos.MovieDTORequest;
import org.luisa.movie.dtos.MovieDTOResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/movies")
public class MovieController {

    private final InterfaceMovieService service;
    private final MovieServiceImpl serviceImpl;

    public MovieController(InterfaceMovieService service, MovieServiceImpl serviceImpl) {
        this.service = service;
        this.serviceImpl = serviceImpl;
    }

    // 1. Obtener todas las películas
    @GetMapping("")
    public List<MovieDTOResponse> index() {
        return service.getEntities();
    }

    // 2. Obtener una película por id
    @GetMapping("{id}")
    public MovieDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 3. Añadir una película
    @PostMapping("")
    public ResponseEntity<MovieDTOResponse> store(@Valid @RequestBody MovieDTORequest dto) {
        MovieDTOResponse dtoResponse = serviceImpl.storeEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    // 4. Actualizar una película
    @PutMapping("{id}")
    public ResponseEntity<MovieDTOResponse> update(@PathVariable Long id, @Valid @RequestBody MovieDTORequest dto) {
        MovieDTOResponse dtoResponse = serviceImpl.updateEntity(id, dto);
        return ResponseEntity.ok(dtoResponse);
    }

    // 5. Eliminar una película
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceImpl.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Buscar por título o género
    @GetMapping("/search")
    public List<MovieDTOResponse> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {

        if (title != null && !title.isBlank()) {
            return serviceImpl.findByTitle(title);
        }
        if (genre != null && !genre.isBlank()) {
            return serviceImpl.findByGenre(genre);
        }
        return service.getEntities();
    }
}
