package org.luisa.country;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.luisa.country.dtos.CountryDTORequest;
import org.luisa.country.dtos.CountryDTOResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl service;

    @Mock
    private CountryRepository repository;

    @BeforeEach
    void setUp() {
        service = new CountryServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        List<CountryEntity> countriesMock = List.of(
                new CountryEntity(1L, "France"),
                new CountryEntity(2L, "Italy")
        );

        when(repository.findAll()).thenReturn(countriesMock);
        List<CountryDTOResponse> countries = service.getEntities();

        assertThat(countries.size(), is(equalTo(2)));
        assertThat(countries.get(0).name(), is(equalTo("France")));
        assertThat(countries.get(1).name(), is(equalTo("Italy")));
    }

    @Test
    void testGetById() {
        CountryEntity countryMock = new CountryEntity(1L, "France");

        when(repository.findById(1L)).thenReturn(Optional.of(countryMock));
        CountryDTOResponse countries = service.getById(1L);

        assertThat(countries.id(), is(equalTo(1L)));
        assertThat(countries.name(), is(equalTo("France")));
    }

    @Test
    void testStoreCountry() {
        CountryDTORequest dto = new CountryDTORequest("Brazil");

        when(repository.save(Mockito.any(CountryEntity.class))).thenReturn(new CountryEntity(1L, dto.name()));
        when(repository.findAll(Mockito.<Example<CountryEntity>>any())).thenReturn(List.of());
        CountryDTOResponse entity = service.storeEntity(dto);

        assertThat(entity.name(), is(equalTo("Brazil")));
    }

    @Test
    void testStoreCountry_CountryExist() {
        CountryDTORequest dto = new CountryDTORequest("Brazil");
        CountryEntity country = new CountryEntity(1L, "Brazil");

        when(repository.findAll(Mockito.<Example<CountryEntity>>any())).thenReturn(List.of(country));
        CountryDTOResponse entity = service.storeEntity(dto);

        assertThat(entity, nullValue());
    }
}
