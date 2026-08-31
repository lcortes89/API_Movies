package org.luisa.actor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.luisa.actor.dtos.ActorDTOResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ActorController.class)
class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceActorService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnAnActor() throws Exception {
        ActorDTOResponse dto = new ActorDTOResponse(1L, "Keanu Reeves");
        List<ActorDTOResponse> actors = new ArrayList<>();
        actors.add(dto);
        String json = mapper.writeValueAsString(actors);

        when(service.getEntities()).thenReturn(actors);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/actors"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("Keanu Reeves"));
    }

    @Test
    void testgetById_ShouldActorById() throws Exception {
        ActorDTOResponse dto = new ActorDTOResponse(1L, "Keanu Reeves");
        String json = mapper.writeValueAsString(dto);

        when(service.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/actors/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }
}
