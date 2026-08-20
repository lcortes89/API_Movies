package org.factoriaf5.pet;

import java.util.List;

import org.factoriaf5.implementations.InterfaceGenericService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements InterfaceGenericService<PetEntity> {

    @Override
    public List<PetEntity> getEntities() {
        List<PetEntity> pets = List.of(new PetEntity(1L, "Max"), new PetEntity(2L, "Bella"));
        return pets;
    }

    @Override
    public PetEntity getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

}
