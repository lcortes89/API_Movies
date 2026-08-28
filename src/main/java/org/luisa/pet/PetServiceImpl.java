package org.factoriaf5.pet;

import java.util.List;

import org.factoriaf5.country.dtos.CountryDTOResponse;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements InterfaceGenericGetService<PetEntity, Object> {

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

    @Override
    public PetEntity getByName(Object text) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public List<PetEntity> getByNameStartingWith(String letter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByNameStartingWith'");
    }

    @Override
    public List<PetEntity> getBySyllable(String syllable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBySyllable'");
    }

}
