package com.which.forensics.service.impl;

import com.which.forensics.dao.LocationDao;
import com.which.forensics.domain.LocationResponse;
import com.which.forensics.entity.Locations;
import com.which.forensics.exception.ForensicApplicationException;
import com.which.forensics.service.LocationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;

    @Override
    public LocationResponse getLocation(String xCoordinate, String yCoordinate) throws ForensicApplicationException {
        Locations locations = locationDao.getLocations(xCoordinate, yCoordinate);
        return new LocationResponse(locations.getLocations());
    }
}
