package com.which.forensics.dao.impl;

import com.which.forensics.dao.DirectionsDao;
import com.which.forensics.entity.Directions;
import org.springframework.stereotype.Component;

@Component
public class DirectionsDaoImpl implements DirectionsDao {

    @Override
    public Directions getDirections() {
        return new Directions("1011");
    }
}
