package org.factoriaf5.pet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "${api-endpoint}/pets")
public class PetController {

    private final InterfaceGenericGetService<PetEntity> service;

    public PetController(InterfaceGenericGetService<PetEntity> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<PetEntity> getPets() {
        return service.getEntities();
    }
    

}
