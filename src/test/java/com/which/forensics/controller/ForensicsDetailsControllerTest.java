package com.which.forensics.controller;

import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.domain.LocationResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ForensicsDetailsControllerTest {

    @InjectMocks
    ForensicsDetailsController forensicsDetailsController;

    private MockMvc mockMvc;
    DirectionsResponse directionsResponse;
    LocationResponse locationResponse;

    @BeforeAll
    //@TestInstance(TestInstance.Lifecycle.PER_CLASS)//
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(forensicsDetailsController).build();
        directionsResponse = new DirectionsResponse();
        locationResponse = new LocationResponse();
    }

    @Test
    public void testDirectionsAPIwith_Success() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@gmail.com/directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testDirectionsAPIwith_NoEmail() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("//directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testLocationAPIwith_Success() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@abc.com/location/xValue/yValue")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testLocationAPIwith_NoEmail() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("//location/xValue/yValue")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testLocationAPIwith_NoXCoordinate() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@abc.com/location//yValue")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testLocationAPIwith_NoYCoordinate() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@abc.com/location/xValue/")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testLocations_ExceedNoOfAttempts() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("//directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(5).isLessThan(6);
    }

    @Test
    public void testForLocations_ValidAttempt() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("//directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(4).isLessThan(6);
    }

    @Test
    public void testForLocations_EmptyLocations() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("//directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
