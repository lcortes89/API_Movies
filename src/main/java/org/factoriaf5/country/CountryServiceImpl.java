package org.factoriaf5.country;

import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.country.dtos.CountryDTORequest;
import org.factoriaf5.country.dtos.CountryDTOResponse;
import org.factoriaf5.country.exceptions.CountryExceptionNotFound;
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

    /**
     * @param id
     * @return
     * 
     * Exceptions:
     * 
     * Types:
     * 1. Checked exceptions -> se comprueban en tiempo de compilación y se usan con un try-catch
     * a. IOException
     * b. FileNotFoundException
     * c. ParseException
     * d. SQLException
     * e. ClassNotFoundException
     * f. InterruptedException
     * i. MalformedURLException
     * 
     * 2. Errors -> fuera de control del developer
     * 
     * 3. Unchecked Exceptions -> no se comprueban en tiempo de compilación sino en tiempo de ejecución. no se necesita un try-catch. Se heredan de la clase RuntimeException.
     * 
     * link: https://www.javatpoint.com/list-of-checked-exceptions-in-java
     * 
     */
    @Override
    public CountryDTOResponse getById(Long id) {
        CountryEntity country = repository.findById(id).orElseThrow(() -> new CountryExceptionNotFound("Country not found. Id " + id + " does not exist."));
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
