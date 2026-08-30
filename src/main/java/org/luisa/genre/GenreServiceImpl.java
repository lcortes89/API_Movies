package org.luisa.genre;

import java.util.List;

import org.luisa.genre.dtos.GenreDTORequest;
import org.luisa.genre.dtos.GenreDTOResponse;
import org.luisa.genre.exceptions.GenreExceptionNotFound;
import org.luisa.genre.mappers.GenreMapper;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements InterfaceGenreService {

    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GenreDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(GenreMapper::toDTO)
                .toList();
    }

    @Override
    public GenreDTOResponse getById(Long id) {
        GenreEntity genre = repository.findById(id)
                .orElseThrow(() -> new GenreExceptionNotFound("Genre not found. Id " + id + " does not exist."));
        return GenreMapper.toDTO(genre);
    }

    public GenreDTOResponse storeEntity(GenreDTORequest dto) {
        GenreEntity genreToSave = GenreMapper.toEntity(dto);

        Example<GenreEntity> example = Example.of(genreToSave);
        boolean alreadyExists = !repository.findAll(example).isEmpty();

        if (alreadyExists)
            return null;

        GenreEntity genreSaved = repository.save(genreToSave);
        return GenreMapper.toDTO(genreSaved);
    }

    public GenreDTOResponse updateEntity(Long id, GenreDTORequest dto) {
        boolean genreExists = repository.existsById(id);
        if (!genreExists)
            throw new GenreExceptionNotFound("Genre not found. Id " + id + " does not exist.");

        boolean nameExists = repository.existsByNameAndIdNot(dto.name(), id);
        if (nameExists)
            return null;

        GenreEntity genreToUpdate = new GenreEntity(id, dto.name());
        GenreEntity genreUpdated = repository.save(genreToUpdate);
        return GenreMapper.toDTO(genreUpdated);
    }
}
