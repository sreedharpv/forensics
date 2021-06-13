package com.which.forensics.service.impl;

import com.which.forensics.dao.DirectionsDao;
import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.entity.Directions;
import com.which.forensics.service.DirectionsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DirectionsServiceImpl implements DirectionsService {

    @Autowired
    DirectionsDao directionsDao;

    @Override
    public DirectionsResponse getDirections() {
        return new DirectionsResponse(directionsDao.getDirections().getDirections());
    }
}
