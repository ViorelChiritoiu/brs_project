package com.brs.service.bus;

import com.brs.dto.model.bus.*;
import com.brs.dto.model.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public interface BusReservationService {
    //Stop related methods
    Set<StopDto> getAllStops();
    StopDto getStopByCode(String stopCode);

    //Agency related methods
    AgencyDto getAgency(UserDto userDto);
    AgencyDto addAgency(AgencyDto agencyDto);
    AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto);

    //Trip related methods
    TripDto getTripById(Long tripId);
    List<TripDto> addTrip(TripDto tripDto);
    List<TripDto> getAgencyTrips(String agencyCode);
    List<TripDto> getAvailableTripsBetweenStops(String sourceStopCode, String destinationStopCode);

    //Trips Schedule related methods
    List<TripScheduleDto> getAvailableTripSchedules(String sourceStopCode, String destinationStopCode, Date tripDate);
    TripScheduleDto getTripSchedule(TripDto tripDto, Date tripDate, boolean createScheduleForTrip);

    //Ticket related method
    TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto passenger);

}
