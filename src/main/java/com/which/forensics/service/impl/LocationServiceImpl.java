package com.which.forensics.service.impl;

import com.which.forensics.dao.LocationDao;
import com.which.forensics.domain.LocationResponse;
import com.which.forensics.dto.MapDetails;
import com.which.forensics.exception.ForensicApplicationException;
import com.which.forensics.service.LoadDirectionsMap;
import com.which.forensics.service.LocationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;
    @Autowired
    LoadDirectionsMap loadDirectionsMap;

    @Override
    public LocationResponse getLocation(String xCoordinate, String yCoordinate) throws ForensicApplicationException {

        //get the directions list
        List<MapDetails> mapDetails = loadDirectionsMap.getMapDetails();
        MapDetails details = mapDetails.stream()
                .filter(mapDetail -> (xCoordinate.equals(mapDetail.getXCoOrdinate())
                        && yCoordinate.equals(mapDetail.getYCoOrdinate())))
                .findFirst()
                .orElse(null);
        //Return location found or not found
        return new LocationResponse(Optional.ofNullable(details).map(MapDetails::getLocation).orElse("Not Found")
                , Optional.ofNullable(details).map(MapDetails::getIsLocated).orElse("Not Found"));
    }
}
