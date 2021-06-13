package com.which.forensics.dao;

import com.which.forensics.entity.Locations;
import com.which.forensics.exception.ForensicApplicationException;

public interface LocationDao {
    Locations getLocations(String xValue, String yValue) throws ForensicApplicationException;
}
