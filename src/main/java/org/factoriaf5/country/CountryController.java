package org.factoriaf5.country;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

import org.factoriaf5.country.dtos.CountryDTORequest;
import org.factoriaf5.country.dtos.CountryDTOResponse;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.factoriaf5.implementations.InterfaceGenericeEditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "${api-endpoint}/countries")
public class CountryController {

    private final InterfaceGenericGetService<CountryDTOResponse> getService;
    private final InterfaceGenericeEditService<CountryDTORequest, CountryDTOResponse> editService;

    public CountryController(
        InterfaceGenericGetService<CountryDTOResponse> getService,
        InterfaceGenericeEditService<CountryDTORequest, CountryDTOResponse> editService) {
        this.getService = getService;
        this.editService = editService;
    }

    @GetMapping("")
    public List<CountryDTOResponse> index() {
        return getService.getEntities();
    }

    @GetMapping("{id}")
    public CountryDTOResponse getById(@PathVariable Long id) {
        return getService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<CountryDTOResponse> store(@Valid @RequestBody CountryDTORequest dto) {

        CountryDTOResponse dtoResponse = editService.storeEntity(dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(201).body(dtoResponse);

        // Buena práctica para añadir la 'Location'(path) en el header de respuesta
        // return ResponseEntity.created(URI.create("/countries/" + dtoResponse.id())).body(dtoResponse);
    }

}
