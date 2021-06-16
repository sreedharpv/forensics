package com.which.forensics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ForensicsApplicationIntegrationTests {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                //.apply(springSecurity())
                .build();
    }

    @Test
    void whenLocationRequestValid_returnSuccess() throws Exception {

        //given

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/sridhar@abc.com/location/xValue/yValue")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void whenLocationRequestInValid_returnFail() throws Exception {

        //given

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("//location/xValue/yValue")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void whenDirectionRequestValid_returnSuccess() throws Exception {

        //given

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/sridhar@abc.com/directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void whenDirectionRequestInValid_returnFail() throws Exception {

        //given

        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/sridhar@abc.com//")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
