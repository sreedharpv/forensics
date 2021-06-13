package com.which.forensics.dao.impl;

import com.which.forensics.domain.LocationResponse;
import com.which.forensics.service.LocationService;

public class LocationDaoImpl implements LocationService {

    @Override
    public LocationResponse getLocation(String xCoordinate, String yCoordinate) {
        return new LocationResponse("London");
    }
}
