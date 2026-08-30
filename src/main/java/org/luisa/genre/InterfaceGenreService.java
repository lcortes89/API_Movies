package org.luisa.genre;

import java.util.List;

import org.luisa.genre.dtos.GenreDTOResponse;

public interface InterfaceGenreService {

    List<GenreDTOResponse> getEntities();

    GenreDTOResponse getById(Long id);
}
EOFcat > src/main/java/org/luisa/genre/InterfaceGenreService.java << 'EOF'
package org.luisa.genre;

import java.util.List;

import org.luisa.genre.dtos.GenreDTOResponse;

public interface InterfaceGenreService {

    List<GenreDTOResponse> getEntities();

    GenreDTOResponse getById(Long id);
}
