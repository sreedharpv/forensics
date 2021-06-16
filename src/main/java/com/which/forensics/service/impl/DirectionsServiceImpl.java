package com.which.forensics.service.impl;

import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.exception.ForensicApplicationException;
import com.which.forensics.service.DirectionsService;
import com.which.forensics.service.LoadDirectionsMap;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DirectionsServiceImpl implements DirectionsService {

    @Autowired
    LoadDirectionsMap loadDirectionsMap;

    /*
    * Gets the directions of the person
    * */
    @Override
    public DirectionsResponse getDirections() throws ForensicApplicationException {
        return loadDirectionsMap.loadDirections();
    }
}
