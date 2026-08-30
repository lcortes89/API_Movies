package org.luisa.home;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testIndex_ShouldReturnHelloMessage() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(get(""))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), containsString("Hello, Spring Boot"));

    }

    @Test
    void testPassingParams_ShouldReturnMessageWithParam() throws Exception {

        mockMvc.perform(get("/passing-param")
                .param("msg", "This is a param")
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk());

    }

    @Test
    void testGetParams() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("name", "pepita");
        params.put("country", "spain");

        MockHttpServletResponse response = mockMvc.perform(get("/params")
                .params(MultiValueMap.fromSingleValue(params))
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(), containsString("pepita"));
        assertThat(response.getContentAsString(), containsString("spain"));
    }

}
