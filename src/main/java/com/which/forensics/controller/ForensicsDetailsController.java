package com.which.forensics.controller;

import com.which.forensics.component.InMemoryCache;
import com.which.forensics.domain.DirectionsResponse;
import com.which.forensics.domain.LocationResponse;
import com.which.forensics.exception.ForensicApplicationException;
import com.which.forensics.exception.ValidationException;
import com.which.forensics.service.DirectionsService;
import com.which.forensics.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.lang.invoke.MethodHandles;

import static com.which.forensics.constants.ForensicsConstants.*;

@RestController
@Api(value = "Forensic APIs")
@Validated
public class ForensicsDetailsController extends AbstractForensicsController {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    DirectionsService directionsService;
    @Autowired
    LocationService locationService;
    @Autowired
    InMemoryCache inMemoryCache;

    /**
     * Method to return directions
     *
     * @param request
     * @param email
     * @param authToken
     * @return DirectionsResponse
     */
    @Validated
    @GetMapping(value = DIRECTIONS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectionsResponse> directions(HttpServletRequest request,
                                                         @ApiParam(value = "API to get directions")
                                                         @PathVariable("email") @NotNull String email,
                                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken
    ) throws ForensicApplicationException {
        LOG.info("Start Directions API {}", DIRECTIONS);
        //Validate Email
        if(isEmptyString.apply(email)) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(),EMPTY_EMAIL_REQUEST_MESSAGE);
        }
        return ResponseEntity.ok(directionsService.getDirections());
    }

    /**
     * Method to get locations
     *
     * @param request
     * @param email
     * @param xCoordinate
     * @param yCoordinate
     * @param authToken
     * @return LocationResponse
     */
    @Validated
    @GetMapping(value = LOCATIONS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResponse> location(HttpServletRequest request,
                                                     @ApiParam(value = "API to get location")
                                                     @PathVariable("email") @NotNull String email,
                                                     @PathVariable("x") String xCoordinate,
                                                     @PathVariable("y") String yCoordinate,
                                                     @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken
    ) throws ForensicApplicationException, ValidationException {
        LOG.info("Start Directions API {}", LOCATIONS);
        Integer noOfAPICalls = 0;

        if(isEmptyString.apply(email)) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(),EMPTY_EMAIL_REQUEST_MESSAGE);
        }
        // Check for the Cache size
        validateNoOfAPICalls(noOfAPICalls);

        return ResponseEntity.ok(locationService.getLocation(xCoordinate, yCoordinate));
    }

    /*This method validates the no of locations requests and returns error message.
    Cache will be invalidated in 5 mins*/
    private void validateNoOfAPICalls(Integer noOfAPICalls) {
        if(inMemoryCache.size() > 0) {
            noOfAPICalls = (Integer) inMemoryCache.get(NO_OF_API_CALLS);
        }
        //Cache expires after 5 mins
        inMemoryCache.add(NO_OF_API_CALLS, ++noOfAPICalls, CACHE_EXPIRY_TIME);

        LOG.info("noOfCalls::{}, Size::{}", noOfAPICalls, inMemoryCache.size());

        //Return error message when no of API calls reached to 5 times.
        if(noOfAPICalls > 5) {
            throw new ValidationException(HttpStatus.TOO_MANY_REQUESTS.value(), TOO_MANY_REQUESTS_MESSAGE);
        }
    }


}
