package org.factoriaf5.pet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "${api-endpoint}/pets")
public class PetController {

    private final InterfacePetService service;

    public PetController(InterfacePetService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<PetEntity> getPets() {
        return service.getPets();
    }
    

}
