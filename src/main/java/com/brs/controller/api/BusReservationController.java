package com.brs.controller.api;

import com.brs.dto.model.bus.StopDto;
import com.brs.service.bus.BusReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class BusReservationController {

    @Autowired
    private BusReservationService busReservationService;

    @ApiOperation(value = "Get a list with all available stops", response = List.class,
            authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No stops found"),
    })
    @GetMapping(value = "/stops", produces = "application/json")
    public ResponseEntity<Set<StopDto>> getAllStops() throws NotFoundException {
        Set<StopDto> stops = busReservationService.getAllStops();
        if(!stops.isEmpty()) {
            return ResponseEntity.ok().body(stops);
        }
        throw new NotFoundException("Stops not found");
    }
}
