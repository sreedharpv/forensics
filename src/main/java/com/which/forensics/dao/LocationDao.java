package com.which.forensics.dao;

import com.which.forensics.entity.Locations;

public interface LocationDao {
    Locations getLocations(String xValue, String yValue);
}
