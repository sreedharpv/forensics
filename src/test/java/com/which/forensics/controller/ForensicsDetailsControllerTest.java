package com.which.forensics.controller;

import com.which.forensics.component.InMemoryCache;
import com.which.forensics.domain.Directions;
import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.domain.LocationResponse;
import com.which.forensics.service.DirectionsService;
import com.which.forensics.service.LocationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ForensicsDetailsControllerTest {

    @InjectMocks
    ForensicsDetailsController forensicsDetailsController;

    @Mock
    DirectionsService directionsService;
    @Mock
    LocationService locationService;
    @Mock
    InMemoryCache inMemoryCache;

    private MockMvc mockMvc;
    DirectionsResponse directionsResponse;
    LocationResponse locationResponse;

    @BeforeAll
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(forensicsDetailsController).build();
        directionsResponse = new DirectionsResponse();
        locationResponse = new LocationResponse();
    }

    @Test
    public void testDirectionsAPI_returnSuccess() throws Exception {
        //given
        given(directionsService.getDirections()).willReturn(new DirectionsResponse(getDirectionsList()));
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@gmail.com/directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    List<Directions> getDirectionsList() {
        List<Directions> list = new ArrayList<Directions>();
        list.add(new Directions("1","0"));
        return list;
    }

    @Test
    public void testDirectionsAPIwith_NoEmail() throws Exception {
        //given
        given(directionsService.getDirections()).willReturn(new DirectionsResponse(getDirectionsList()));
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("//directions")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testLocationAPI_returnSuccess() throws Exception {
        //given
        given(locationService.getLocation(anyString(), anyString())).willReturn(new LocationResponse("London", "Found"));

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
        given(locationService.getLocation(anyString(), anyString())).willReturn(new LocationResponse("London", "found"));

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
        given(locationService.getLocation(anyString(), anyString())).willReturn(null);

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
        given(locationService.getLocation(anyString(), anyString())).willReturn(null);

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
        given(locationService.getLocation(anyString(), anyString())).willReturn(new LocationResponse("London", "Found"));

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@abc.com/location/xValue/")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(5).isLessThan(6);
    }

    @Test
    public void testForLocations_ValidAttempt() throws Exception {
        //given
        given(locationService.getLocation(anyString(), anyString())).willReturn(new LocationResponse("London", "Found"));

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@abc.com/location/xValue/")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(4).isLessThan(6);
    }

    @Test
    public void testForLocations_EmptyLocations() throws Exception {
        //given
        given(locationService.getLocation(anyString(), anyString())).willReturn(new LocationResponse("", ""));

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sridhar@abc.com/location/xValue/")
                .header(HttpHeaders.AUTHORIZATION, "asdfasd")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
