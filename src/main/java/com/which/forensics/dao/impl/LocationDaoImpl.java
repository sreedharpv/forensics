package com.which.forensics.dao.impl;

import com.which.forensics.dao.LocationDao;
import com.which.forensics.entity.Locations;
import com.which.forensics.exception.ForensicApplicationException;
import org.springframework.stereotype.Component;

@Component
public class LocationDaoImpl implements LocationDao {

    @Override
    public Locations getLocations(String xValue, String yValue) throws ForensicApplicationException {
        return new Locations("London");
    }
}
