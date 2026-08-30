package org.luisa.year;

import java.util.List;

import org.luisa.year.dtos.YearDTORequest;
import org.luisa.year.dtos.YearDTOResponse;

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
@RequestMapping(path = "${api-endpoint}/years")
public class YearController {

    private final InterfaceYearService service;

    public YearController(InterfaceYearService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<YearDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public YearDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<YearDTOResponse> store(@Valid @RequestBody YearDTORequest dto) {
        YearDTOResponse dtoResponse = service.storeEntity(dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<YearDTOResponse> update(@PathVariable Long id, @Valid @RequestBody YearDTORequest dto) {
        YearDTOResponse dtoResponse = service.updateEntity(id, dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.ok(dtoResponse);
    }
}
