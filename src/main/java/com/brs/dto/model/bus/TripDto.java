package com.brs.dto.model.bus;

import java.util.Date;

public class TripDto {

    private Long id;
    private Integer fare;
    private Date journeyTime;
    private String sourceStopCode;
    private String sourceStopName;
    private String destinationStopCode;
    private String destinationStopName;
    private String busCode;
    private String agencyCode;

    public TripDto() {
    }

    public TripDto(Long id, Integer fare, Date journeyTime, String sourceStopCode, String getSourceStopName,
                   String destinationStopCode, String destinationStopName, String busCode, String agencyCode) {
        this.id = id;
        this.fare = fare;
        this.journeyTime = journeyTime;
        this.sourceStopCode = sourceStopCode;
        this.sourceStopName = getSourceStopName;
        this.destinationStopCode = destinationStopCode;
        this.destinationStopName = destinationStopName;
        this.busCode = busCode;
        this.agencyCode = agencyCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSourceStopCode() {
        return sourceStopCode;
    }

    public void setSourceStopCode(String sourceStopCode) {
        this.sourceStopCode = sourceStopCode;
    }

    public String getSourceStopName() {
        return sourceStopName;
    }

    public void setSourceStopName(String sourceStopName) {
        this.sourceStopName = sourceStopName;
    }

    public String getDestinationStopCode() {
        return destinationStopCode;
    }

    public void setDestinationStopCode(String destinationStopCode) {
        this.destinationStopCode = destinationStopCode;
    }

    public String getDestinationStopName() {
        return destinationStopName;
    }

    public void setDestinationStopName(String destinationStopName) {
        this.destinationStopName = destinationStopName;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    @Override
    public String toString() {
        return "TripDto{" +
                "id='" + id + '\'' +
                ", fare=" + fare +
                ", journeyTime=" + journeyTime +
                ", sourceStopCode='" + sourceStopCode + '\'' +
                ", sourceStopName='" + sourceStopName + '\'' +
                ", destinationStopCode='" + destinationStopCode + '\'' +
                ", destinationStopName='" + destinationStopName + '\'' +
                ", busCode='" + busCode + '\'' +
                ", agencyCode='" + agencyCode + '\'' +
                '}';
    }
}
