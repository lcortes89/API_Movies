package org.luisa.year;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.luisa.year.dtos.YearDTORequest;
import org.luisa.year.dtos.YearDTOResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

@ExtendWith(MockitoExtension.class)
class YearServiceImplTest {

    @InjectMocks
    private YearServiceImpl service;

    @Mock
    private YearRepository repository;

    @BeforeEach
    void setUp() {
        service = new YearServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        List<YearEntity> yearsMock = List.of(
                new YearEntity(1L, 1999),
                new YearEntity(2L, 2010)
        );

        when(repository.findAll()).thenReturn(yearsMock);
        List<YearDTOResponse> years = service.getEntities();

        assertThat(years.size(), is(equalTo(2)));
        assertThat(years.get(0).releaseYear(), is(equalTo(1999)));
        assertThat(years.get(1).releaseYear(), is(equalTo(2010)));
    }

    @Test
    void testGetById() {
        YearEntity yearMock = new YearEntity(1L, 1999);

        when(repository.findById(1L)).thenReturn(Optional.of(yearMock));
        YearDTOResponse year = service.getById(1L);

        assertThat(year.id(), is(equalTo(1L)));
        assertThat(year.releaseYear(), is(equalTo(1999)));
    }

    @Test
    void testStoreYear() {
        YearDTORequest dto = new YearDTORequest(1994);
        YearEntity savedEntity = new YearEntity(1L, dto.releaseYear());

        when(repository.save(Mockito.any(YearEntity.class))).thenReturn(savedEntity);
        when(repository.findAll(Mockito.<Example<YearEntity>>any())).thenReturn(List.of());
        YearDTOResponse entity = service.storeEntity(dto);

        assertThat(entity.releaseYear(), is(equalTo(1994)));
    }

    @Test
    void testStoreYear_YearExist() {
        YearDTORequest dto = new YearDTORequest(1994);
        YearEntity year = new YearEntity(1L, 1994);

        when(repository.findAll(Mockito.<Example<YearEntity>>any())).thenReturn(List.of(year));
        YearDTOResponse entity = service.storeEntity(dto);

        assertThat(entity, nullValue());
    }

    @Test
    void testUpdateYear() {
        YearDTORequest dto = new YearDTORequest(2015);
        YearEntity updatedEntity = new YearEntity(1L, 2015);

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByReleaseYearAndIdNot(2015, 1L)).thenReturn(false);
        when(repository.save(Mockito.any(YearEntity.class))).thenReturn(updatedEntity);

        YearDTOResponse entity = service.updateEntity(1L, dto);

        assertThat(entity.releaseYear(), is(equalTo(2015)));
    }

    @Test
    void testUpdateYear_YearExist() {
        YearDTORequest dto = new YearDTORequest(1994);

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByReleaseYearAndIdNot(1994, 1L)).thenReturn(true);

        YearDTOResponse entity = service.updateEntity(1L, dto);

        assertThat(entity, nullValue());
    }
}
