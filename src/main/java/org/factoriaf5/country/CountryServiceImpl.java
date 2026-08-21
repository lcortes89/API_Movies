package org.factoriaf5.country;

import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.country.dtos.CountryDTORequest;
import org.factoriaf5.country.dtos.CountryDTOResponse;
import org.factoriaf5.country.mappers.CountryMapper;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.factoriaf5.implementations.InterfaceGenericeEditService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements InterfaceGenericGetService<CountryDTOResponse>,
                                            InterfaceGenericeEditService<CountryDTORequest, CountryDTOResponse> {

    private final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    public List<CountryDTOResponse> getEntities() {
        List<CountryDTOResponse> countries = new ArrayList<>();

        repository.findAll().forEach(c -> {
            CountryDTOResponse country = CountryMapper.toDTO(c);
            countries.add(country);
        });

        return countries;
    }

    @Override
    public CountryDTOResponse getById(Long id) {
        CountryEntity country = repository.findById(id).orElseThrow();
        return CountryMapper.toDTO(country);
    }

    @Override
    public CountryDTOResponse storeEntity(CountryDTORequest dto) {

        CountryEntity countryToSave = CountryMapper.toEntity(dto);

        // Comprobar si existe - lógica de negocio
        // https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html#query-by-example.fluent
        Example<CountryEntity> example = Example.of(countryToSave);
        boolean isEmpty  = repository.findAll(example).isEmpty();

        if (!isEmpty) return null;

        CountryEntity countrySaved = repository.save(countryToSave);

        return CountryMapper.toDTO(countrySaved);
    }

}
