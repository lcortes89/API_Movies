package org.factoriaf5.country;

import java.util.List;

import org.factoriaf5.implementations.InterfaceGenericService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements InterfaceGenericService<CountryEntity> {

    private final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    public List<CountryEntity> getEntities() {
        return repository.findAll();
    }

    @Override
    public CountryEntity getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

}
