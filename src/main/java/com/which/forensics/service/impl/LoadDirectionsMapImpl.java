package com.which.forensics.service.impl;

import com.which.forensics.domain.Directions;
import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.dto.Location;
import com.which.forensics.dto.MapDetails;
import com.which.forensics.exception.ForensicApplicationException;
import com.which.forensics.service.LoadDirectionsMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class loads the maps data from csv file
 */

@Service
public class LoadDirectionsMapImpl implements LoadDirectionsMap {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    ResourceLoader resourceLoader;
    DirectionsResponse directionsResponse;
    List<Location> locationList;
    List<MapDetails> mapDetailsList;

    private static final String DIRECTIONS_MAP_FILE_NAME = "classpath:directions_map.csv";
    private static final String CSV_FILE_SEPARATOR = ",";

    /*
     * This method loads the CSV file only once during server start up
     */
    @PostConstruct
    public void init() throws ForensicApplicationException {
        Resource resource = resourceLoader.getResource(DIRECTIONS_MAP_FILE_NAME);
        directionsResponse = new DirectionsResponse();
        mapDetailsList = new ArrayList<MapDetails>();
        List<Directions> directionsList = new ArrayList<Directions>();
        locationList = new ArrayList<Location>();
        BufferedReader br = null;
        LOG.info("Loading the Data file");
        try{
            br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            mapDetailsList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            for(MapDetails details: mapDetailsList) {
                directionsList.add(new Directions(details.getXCoOrdinate(), details.getYCoOrdinate()));
                locationList.add(new Location(details.getLocation(), details.getIsLocated()));
            }
            directionsResponse.setPositions(directionsList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Function<String, MapDetails> mapToItem = (line) -> {

        String[] position = line.split(CSV_FILE_SEPARATOR);// a CSV has comma separated lines
        MapDetails mapDetails = new MapDetails();
        mapDetails.setXCoOrdinate(position[0]);//<-- this is the first column in the csv file
        mapDetails.setYCoOrdinate(position[1]);
        mapDetails.setLocation(position[2]);
        mapDetails.setIsLocated(position[3]);
        return mapDetails;
    };

    @Override
    public DirectionsResponse loadDirections() throws ForensicApplicationException {
        return directionsResponse;
    }

    @Override
    public List<Location> loadLocations() throws ForensicApplicationException {
        return locationList;
    }

    @Override
    public List<MapDetails> getMapDetails() throws ForensicApplicationException {
        return mapDetailsList;
    }
}
