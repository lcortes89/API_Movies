package org.factoriaf5.pet;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements InterfacePetService {

    @Override
    public List<PetEntity> getPets() {
        List<PetEntity> pets = List.of(new PetEntity(1L, "Max"), new PetEntity(2L, "Bella"));
        return pets;
    }

}
