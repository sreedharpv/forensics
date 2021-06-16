package com.which.forensics.service;

import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.dto.Location;
import com.which.forensics.dto.MapDetails;
import com.which.forensics.exception.ForensicApplicationException;

import java.util.List;

public interface LoadDirectionsMap {
    DirectionsResponse loadDirections() throws ForensicApplicationException;
    List<Location> loadLocations() throws ForensicApplicationException;
    List<MapDetails> getMapDetails() throws ForensicApplicationException;
}
