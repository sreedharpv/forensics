package com.which.forensics.service;

import com.which.forensics.domain.LocationResponse;
import com.which.forensics.exception.ForensicApplicationException;

public interface LocationService {
    LocationResponse getLocation(String xCoordinate, String yCoordinate) throws ForensicApplicationException;
}
