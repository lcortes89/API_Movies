package org.luisa.movie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.luisa.movie.dtos.MovieDTORequest;
import org.luisa.movie.dtos.MovieDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceMovieService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnAMovie() throws Exception {
        MovieDTOResponse dto = new MovieDTOResponse(1L, "The Matrix", "synopsis", null, null, List.of());
        List<MovieDTOResponse> movies = new ArrayList<>();
        movies.add(dto);
        String json = mapper.writeValueAsString(movies);

        when(service.getEntities()).thenReturn(movies);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("The Matrix"));
    }

    @Test
    void testgetById_ShouldMovieById() throws Exception {
        MovieDTOResponse dto = new MovieDTOResponse(1L, "The Matrix", "synopsis", null, null, List.of());
        String json = mapper.writeValueAsString(dto);

        when(service.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test
    void testStore_ShouldReturnCreated() throws Exception {
        MovieDTORequest dtoRequest = new MovieDTORequest("The Matrix", "synopsis", 1L, 1L, null);
        MovieDTOResponse dtoResponse = new MovieDTOResponse(1L, "The Matrix", "synopsis", null, null, List.of());
        String requestJson = mapper.writeValueAsString(dtoRequest);
        String responseJson = mapper.writeValueAsString(dtoResponse);

        when(service.storeEntity(dtoRequest)).thenReturn(dtoResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(201)));
        assertThat(response.getContentAsString(), is(equalTo(responseJson)));
    }

    @Test
    void testStore_ShouldReturnBadRequest_WhenTitleIsBlank() throws Exception {
        String invalidJson = "{\"title\":\"\",\"synopsis\":\"synopsis\",\"yearId\":1,\"genreId\":1}";

        mockMvc.perform(post("/api/v1/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate_ShouldReturnOk() throws Exception {
        MovieDTORequest dtoRequest = new MovieDTORequest("The Matrix Reloaded", "synopsis", 1L, 1L, null);
        MovieDTOResponse dtoResponse = new MovieDTOResponse(1L, "The Matrix Reloaded", "synopsis", null, null, List.of());
        String requestJson = mapper.writeValueAsString(dtoRequest);
        String responseJson = mapper.writeValueAsString(dtoResponse);

        when(service.updateEntity(1L, dtoRequest)).thenReturn(dtoResponse);

        MockHttpServletResponse response = mockMvc.perform(put("/api/v1/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(responseJson)));
    }

    @Test
    void testDelete_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/1"))
                .andExpect(status().isNoContent());

        verify(service).deleteEntity(1L);
    }

    @Test
    void testSearch_ShouldReturnMoviesByTitle() throws Exception {
        MovieDTOResponse dto = new MovieDTOResponse(1L, "The Matrix", "synopsis", null, null, List.of());
        String json = mapper.writeValueAsString(List.of(dto));

        when(service.findByTitle("Matrix")).thenReturn(List.of(dto));

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies/search").param("title", "Matrix"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test
    void testSearch_ShouldReturnMoviesByGenre() throws Exception {
        MovieDTOResponse dto = new MovieDTOResponse(1L, "The Matrix", "synopsis", null, null, List.of());
        String json = mapper.writeValueAsString(List.of(dto));

        when(service.findByGenre("Sci-Fi")).thenReturn(List.of(dto));

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies/search").param("genre", "Sci-Fi"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }
}
