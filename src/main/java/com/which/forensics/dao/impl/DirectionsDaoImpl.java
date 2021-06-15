package com.which.forensics.dao.impl;

import com.which.forensics.dao.DirectionsDao;
import com.which.forensics.entity.Directions;
import com.which.forensics.service.LoadDirectionsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectionsDaoImpl implements DirectionsDao {

    @Autowired
    LoadDirectionsMap loadDirectionsMap;

    @Override
    public Directions getDirections() {
        return new Directions("1011");
    }
}
