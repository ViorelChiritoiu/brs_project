package com.brs.dto.model.bus;

import com.brs.model.bus.Stop;

import java.util.Date;

public class TicketDto {

    private Long id;
    private String busCode;
    private Integer seatNumber;
    private boolean cancellable;
    private Date journeyDate;
    private Stop sourceStop;
    private Stop destinationStop;
    private String passengerName;
    private String passengerMobileNumber;

    public TicketDto() {
    }

    public TicketDto(Long id, String busCode, Integer seatNumber, boolean cancellable, Date journeyDate,
                     Stop sourceStop, Stop destinationStop, String passengerName, String passengerMobileNumber) {
        this.id = id;
        this.busCode = busCode;
        this.seatNumber = seatNumber;
        this.cancellable = cancellable;
        this.journeyDate = journeyDate;
        this.sourceStop = sourceStop;
        this.destinationStop = destinationStop;
        this.passengerName = passengerName;
        this.passengerMobileNumber = passengerMobileNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
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

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerMobileNumber() {
        return passengerMobileNumber;
    }

    public void setPassengerMobileNumber(String passengerMobileNumber) {
        this.passengerMobileNumber = passengerMobileNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id='" + id + '\'' +
                ", busCode='" + busCode + '\'' +
                ", cancellable=" + cancellable +
                ", journeyDate=" + journeyDate +
                ", sourceStop=" + sourceStop +
                ", destinationStop=" + destinationStop +
                ", passengerName='" + passengerName + '\'' +
                ", passengerMobileNumber='" + passengerMobileNumber + '\'' +
                '}';
    }
}
