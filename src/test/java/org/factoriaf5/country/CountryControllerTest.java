package org.factoriaf5.country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.factoriaf5.implementations.InterfaceGenericService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceGenericService<CountryEntity> service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnACountry() throws Exception {
        CountryEntity spain = new CountryEntity(1L, "Spain");
        List<CountryEntity> countries = List.of(spain);
        String json = mapper.writeValueAsString(countries);

        when(service.getEntities()).thenReturn(countries);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/countries"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test
    void testgetById_ShouldCountryById() throws Exception {
        CountryEntity spain = new CountryEntity(1L, "Spain");
        String json = mapper.writeValueAsString(spain);

        when(service.getById(1L)).thenReturn(spain);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/countries/1"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

}
