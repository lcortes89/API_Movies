package org.factoriaf5.country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.country.dtos.CountryDTORequest;
import org.factoriaf5.country.dtos.CountryDTOResponse;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.factoriaf5.implementations.InterfaceGenericeEditService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceGenericGetService<CountryDTOResponse, CountryDTORequest> service;

    @MockitoBean
    private InterfaceGenericeEditService<CountryDTORequest, CountryDTOResponse> serviceEdit;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnACountry() throws Exception {
        CountryDTOResponse dto = new CountryDTOResponse(1L, "Spain");
        List<CountryDTOResponse> countries = new ArrayList<>();
        countries.add(dto);
        String json = mapper.writeValueAsString(countries);

        when(service.getEntities()).thenReturn(countries);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/countries"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("Spain"));
    }

    @Test
    void testgetById_ShouldCountryById() throws Exception {
        CountryDTOResponse dto = new CountryDTOResponse(1L, "Spain");
        String json = mapper.writeValueAsString(dto);

        when(service.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/countries/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

}
