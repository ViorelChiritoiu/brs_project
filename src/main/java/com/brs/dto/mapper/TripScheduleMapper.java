package com.brs.dto.mapper;

/*@Mapper
public interface TripScheduleMapper {
    TripScheduleDto toTripScheduleDto(TripSchedule tripSchedule);
    List<TripScheduleDto> toTripScheduleDtos(List<TripSchedule> tripSchedules);
    TripSchedule toTripSchedule(TripScheduleDto tripScheduleDto);
}*/

import com.brs.dto.model.bus.TripScheduleDto;
import com.brs.model.bus.TripSchedule;

public class TripScheduleMapper {

    public static TripScheduleDto toTripScheduleDto(TripSchedule tripSchedule) {

        TripScheduleDto tripScheduleDto = new TripScheduleDto();

        tripScheduleDto.setId(tripSchedule.getId());
        tripScheduleDto.setTripId(tripSchedule.getDetail().getId().toString());
        tripScheduleDto.setBusCode(tripSchedule.getDetail().getBus().getCode());
        tripScheduleDto.setAvailableSeats(tripSchedule.getAvailableSeats());
        tripScheduleDto.setFare(tripSchedule.getDetail().getFare());
        tripScheduleDto.setJourneyTime(tripSchedule.getDetail().getJourneyTime());
        tripScheduleDto.setSourceStop(tripSchedule.getDetail().getSourceStop());
        tripScheduleDto.setDestinationStop(tripSchedule.getDetail().getDestStop());

        return tripScheduleDto;

    }
}
