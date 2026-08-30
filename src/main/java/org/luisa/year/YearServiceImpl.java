package org.luisa.year;

import java.util.List;

import org.luisa.year.dtos.YearDTORequest;
import org.luisa.year.dtos.YearDTOResponse;
import org.luisa.year.exceptions.YearExceptionNotFound;
import org.luisa.year.mappers.YearMapper;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class YearServiceImpl implements InterfaceYearService {

    private final YearRepository repository;

    public YearServiceImpl(YearRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<YearDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(YearMapper::toDTO)
                .toList();
    }

    @Override
    public YearDTOResponse getById(Long id) {
        YearEntity year = repository.findById(id)
                .orElseThrow(() -> new YearExceptionNotFound("Year not found. Id " + id + " does not exist."));
        return YearMapper.toDTO(year);
    }

    @Override
    public YearDTOResponse storeEntity(YearDTORequest dto) {
        YearEntity yearToSave = YearMapper.toEntity(dto);

        Example<YearEntity> example = Example.of(yearToSave);
        boolean alreadyExists = !repository.findAll(example).isEmpty();

        if (alreadyExists)
            return null;

        YearEntity yearSaved = repository.save(yearToSave);
        return YearMapper.toDTO(yearSaved);
    }

    @Override
    public YearDTOResponse updateEntity(Long id, YearDTORequest dto) {
        boolean yearExists = repository.existsById(id);
        if (!yearExists)
            throw new YearExceptionNotFound("Year not found. Id " + id + " does not exist.");

        boolean releaseYearExists = repository.existsByReleaseYearAndIdNot(dto.releaseYear(), id);
        if (releaseYearExists)
            return null;

        YearEntity yearToUpdate = new YearEntity(id, dto.releaseYear());
        YearEntity yearUpdated = repository.save(yearToUpdate);
        return YearMapper.toDTO(yearUpdated);
    }
}
