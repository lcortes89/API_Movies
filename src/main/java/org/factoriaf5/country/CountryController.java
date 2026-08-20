package org.factoriaf5.country;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.factoriaf5.implementations.InterfaceGenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "${api-endpoint}/countries")
public class CountryController {

    private final InterfaceGenericService<CountryEntity> service;

    public CountryController(InterfaceGenericService<CountryEntity> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<CountryEntity> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public CountryEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }
    

}
