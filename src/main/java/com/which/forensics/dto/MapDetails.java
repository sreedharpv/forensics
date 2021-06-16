package com.which.forensics.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MapDetails implements Serializable {
    private static final long serialVersionUID = -122322331234324L;

    String xCoOrdinate;
    String yCoOrdinate;
    String location;
    String isLocated;
}
