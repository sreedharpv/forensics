package com.which.forensics.controller;

import java.util.function.Function;

public abstract class AbstractForensicsController {

    public static final String DIRECTIONS = "/{email}/directions";
    public static final String LOCATIONS = "/{email}/location/{x}/{y}";

    public static final Function<String, Boolean> isEmptyString = strValue ->
            (strValue == null || strValue.trim().isEmpty()) ?
                    Boolean.TRUE: Boolean.FALSE;
}
