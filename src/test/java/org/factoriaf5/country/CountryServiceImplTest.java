package org.factoriaf5.country;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl service;

    @Mock
    private CountryRepository repository;

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
        List<CountryEntity> countries = service.getEntities();

        assertThat(countries.size(), is(equalTo(2)));
        assertThat(countries.get(0).getName(), is(equalTo("France")));
        assertThat(countries.get(1).getName(), is(equalTo("Italy")));
    }

    @Test
    void testGetById() {
        CountryEntity countryMock = new CountryEntity(1L, "France");

        when(repository.findById(1L)).thenReturn(Optional.of(countryMock));
        CountryEntity countries = service.getById(1L);

        assertThat(countries.getId(), is(equalTo(1L)));
        assertThat(countries.getName(), is(equalTo("France")));
    }
}
