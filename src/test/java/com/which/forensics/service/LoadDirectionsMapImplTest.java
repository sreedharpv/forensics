package com.which.forensics.service;

import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.service.impl.LoadDirectionsMapImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoadDirectionsMapImplTest {

    @InjectMocks
    LoadDirectionsMapImpl loadDirectionsMap;
    @Mock
    ResourceLoader resourceLoader;

    @BeforeAll
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void init_HappyPath() throws Exception {
        Resource resource = Mockito.mock(Resource.class);
        //Given
        given(resourceLoader.getResource(anyString())).willReturn(resource);
        InputStream inputStream = new ByteArrayInputStream(("line1,line11,line13,line14" +
                "\nline2,line21,line22,line23\nline3,line31,line3,line31").getBytes());
        given(resource.getInputStream()).willReturn(inputStream);
        loadDirectionsMap.init();
        DirectionsResponse response = loadDirectionsMap.loadDirections();
        assertThat(!response.getPositions().isEmpty());
        assertThat(response.getPositions().get(1).getXCoOrdinate().equalsIgnoreCase("line3"));
    }

    @Test
    public void init_NoInputFile() throws Exception {

        Assertions.assertThrows(NullPointerException.class, () -> {
            loadDirectionsMap.init();
        });
        ;
    }
}
