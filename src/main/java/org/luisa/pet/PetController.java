package org.luisa.pet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.luisa.implementations.InterfaceGenericGetService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "${api-endpoint}/pets")
public class PetController {

    private final InterfaceGenericGetService<PetEntity, Object> service;

    public PetController(InterfaceGenericGetService<PetEntity, Object> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<PetEntity> getPets() {
        return service.getEntities();
    }
    

}
