package com.brs.dto.model.bus;

import com.brs.model.bus.Stop;

import java.util.Date;

public class TripScheduleDto {

    private Long id;
    private String tripId;
    private Date tripDate;
    private Integer availableSeats;
    private Integer fare;
    private Date journeyTime;
    private String busCode;
    private Stop sourceStop;
    private Stop destinationStop;

    public TripScheduleDto() {
    }

    public TripScheduleDto(Long id, String tripId, Date tripDate, Integer availableSeats, Integer fare,
                           Date journeyTime, String busCode, Stop sourceStop, Stop destinationStop) {
        this.id = id;
        this.tripId = tripId;
        this.tripDate = tripDate;
        this.availableSeats = availableSeats;
        this.fare = fare;
        this.journeyTime = journeyTime;
        this.busCode = busCode;
        this.sourceStop = sourceStop;
        this.destinationStop = destinationStop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public Date getJourneyTime() {
        return journeyTime;
    }

    public void setJourneyTime(Date journeyTime) {
        this.journeyTime = journeyTime;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public Stop getSourceStop() {
        return sourceStop;
    }

    public void setSourceStop(Stop sourceStop) {
        this.sourceStop = sourceStop;
    }

    public Stop getDestinationStop() {
        return destinationStop;
    }

    public void setDestinationStop(Stop destinationStop) {
        this.destinationStop = destinationStop;
    }

    @Override
    public String toString() {
        return "TripScheduleDto{" +
                "id='" + id + '\'' +
                ", tripId='" + tripId + '\'' +
                ", tripDate=" + tripDate +
                ", availableSeats=" + availableSeats +
                ", fare=" + fare +
                ", journeyTime=" + journeyTime +
                ", busCode='" + busCode + '\'' +
                ", sourceStop=" + sourceStop +
                ", destinationStop=" + destinationStop +
                '}';
    }
}
