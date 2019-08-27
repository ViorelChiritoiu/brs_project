package com.brs.dto.mapper;

/*@Mapper
public interface TripMapper {
    @Mapping(source = "sourceStop", target = "sourceStopcode")
    @Mapping(source = "")
    TripDto toTripDto(Trip trip);
    List<TripDto> toTripDtos(List<Trip> trips);
    Trip toTrip(TripDto tripDto);
}*/

import com.brs.dto.model.bus.TripDto;
import com.brs.model.bus.Trip;

public class TripMapper {

    public static TripDto toTripDto(Trip trip) {

        TripDto tripDto = new TripDto();
        tripDto.setId(trip.getId());
        tripDto.setAgencyCode(trip.getAgency().getCode());
        tripDto.setSourceStopCode(trip.getSourceStop().getCode());
        tripDto.setSourceStopName(trip.getSourceStop().getName());
        tripDto.setDestinationStopCode(trip.getDestStop().getCode());
        tripDto.setDestinationStopName(trip.getDestStop().getName());
        tripDto.setBusCode(trip.getBus().getCode());
        tripDto.setJourneyTime(trip.getJourneyTime());
        tripDto.setFare(trip.getFare());

        return tripDto;
    }
}
