package com.which.forensics.service;

import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.exception.ForensicApplicationException;

public interface LoadDirectionsMap {
    public DirectionsResponse loadDirections() throws ForensicApplicationException;
}
