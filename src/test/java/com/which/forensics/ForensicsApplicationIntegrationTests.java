package com.which.forensics;

import com.which.forensics.dao.DirectionsDao;
import com.which.forensics.dao.LocationDao;
import com.which.forensics.entity.Directions;
import com.which.forensics.entity.Locations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

/*
    Below code is commented since mocking not required for this code. However, we may need to mock the DAO layer when
    data pulls from other data source.
	@MockBean
	DirectionsDao directionsDao;
	@MockBean
	LocationDao locationDao;
*/

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
		//given(locationDao.getLocations(anyString(), anyString())).willReturn(any(Locations.class));
		//when
		RequestBuilder request = MockMvcRequestBuilders.get("/sridhar@abc.com/location/xValue/yValue")
				.header(HttpHeaders.AUTHORIZATION, "asdfasd")
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
		//then

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void whenDirectionRequestValid_returnSuccess() throws Exception {

		//given
		//given(directionsDao.getDirections()).willReturn(any(Directions.class));
		//when
		RequestBuilder request = MockMvcRequestBuilders.get("/sridhar@abc.com/directions")
				.header(HttpHeaders.AUTHORIZATION, "asdfasd")
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
		//then

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

}
