package com.which.forensics.controller;

import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.domain.LocationResponse;
import com.which.forensics.service.DirectionsService;
import com.which.forensics.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;

@RestController
@Api(value = "Forensic APIs")
@Validated
public class ForensicsDetailsController extends AbstractForensicsController {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    DirectionsService directionsService;

    @Autowired
    LocationService locationService;

    /**
     * Method to return directions
     * @param request
     * @param email
     * @param authToken
     * @return DirectionsResponse
     */
    @GetMapping(value = DIRECTIONS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectionsResponse> directions(HttpServletRequest request,
                                                         @ApiParam(value = "API to get directions")
                                                         @PathVariable("email") String email,
                                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken
    ) {

        return ResponseEntity.ok(directionsService.getDirections());
    }

    /**
     * Method to get locations
     * @param request
     * @param email
     * @param xCoordinate
     * @param yCoordinate
     * @param authToken
     * @return LocationResponse
     */
    @GetMapping(value = LOCATIONS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResponse> location(HttpServletRequest request,
                                                         @ApiParam(value = "API to get location")
                                                         @PathVariable("email") String email,
                                                         @PathVariable("x") String xCoordinate,
                                                         @PathVariable("y") String yCoordinate,
                                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken
    ) {

        return ResponseEntity.ok(locationService.getLocation(xCoordinate, yCoordinate));
    }

}
