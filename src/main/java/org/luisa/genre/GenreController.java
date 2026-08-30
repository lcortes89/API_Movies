package org.luisa.genre;

import java.util.List;

import org.luisa.genre.dtos.GenreDTORequest;
import org.luisa.genre.dtos.GenreDTOResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/genres")
public class GenreController {

    private final InterfaceGenreService service;
    private final GenreServiceImpl serviceImpl;

    public GenreController(InterfaceGenreService service, GenreServiceImpl serviceImpl) {
        this.service = service;
        this.serviceImpl = serviceImpl;
    }

    @GetMapping("")
    public List<GenreDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public GenreDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<GenreDTOResponse> store(@Valid @RequestBody GenreDTORequest dto) {
        GenreDTOResponse dtoResponse = serviceImpl.storeEntity(dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<GenreDTOResponse> update(@PathVariable Long id, @Valid @RequestBody GenreDTORequest dto) {
        GenreDTOResponse dtoResponse = serviceImpl.updateEntity(id, dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.ok(dtoResponse);
    }
}
