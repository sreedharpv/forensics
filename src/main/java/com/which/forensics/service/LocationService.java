package com.which.forensics.service;

import com.which.forensics.domain.LocationResponse;

public interface LocationService {
    LocationResponse getLocation(String xCoordinate, String yCoordinate);
}
