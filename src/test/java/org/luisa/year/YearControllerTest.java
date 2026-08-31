package org.luisa.year;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.luisa.year.dtos.YearDTOResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = YearController.class)
class YearControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceYearService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnAYear() throws Exception {
        YearDTOResponse dto = new YearDTOResponse(1L, 1999);
        List<YearDTOResponse> years = new ArrayList<>();
        years.add(dto);
        String json = mapper.writeValueAsString(years);

        when(service.getEntities()).thenReturn(years);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/years"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("1999"));
    }

    @Test
    void testgetById_ShouldYearById() throws Exception {
        YearDTOResponse dto = new YearDTOResponse(1L, 1999);
        String json = mapper.writeValueAsString(dto);

        when(service.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/years/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }
}
