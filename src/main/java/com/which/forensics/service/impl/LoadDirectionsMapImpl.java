package com.which.forensics.service.impl;

import com.which.forensics.domain.Directions;
import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.exception.ForensicApplicationException;
import com.which.forensics.service.LoadDirectionsMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LoadDirectionsMapImpl implements LoadDirectionsMap {

    @Inject
    ResourceLoader resourceLoader;
    DirectionsResponse directionsResponse;

    private static final String DIRECTIONS_MAP_FILE_NAME = "classpath:directions_map.csv";
    private static final String CSV_FILE_SEPARATOR = ",";
    private static final int CSV_FILE_COLUMNS = 2;

    /*
     * This method loads the CSV file only once during server start up
     */
    @PostConstruct
    public void init() throws ForensicApplicationException {
        Resource resource = resourceLoader.getResource(DIRECTIONS_MAP_FILE_NAME);
        directionsResponse = new DirectionsResponse();
        BufferedReader br = null;
        List<Directions> list = new ArrayList<>();

        try{
            br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            list = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            directionsResponse.setPositions(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Function<String, Directions> mapToItem = (line) -> {

        String[] position = line.split(CSV_FILE_SEPARATOR);// a CSV has comma separated lines
        Directions direction = new Directions();
        direction.setXCoOrdinate(position[0]);//<-- this is the first column in the csv file
        direction.setYCoOrdinate(position[1]);

        return direction;
    };

    @Override
    public DirectionsResponse loadDirections() throws ForensicApplicationException {
        return directionsResponse;
    }
}
