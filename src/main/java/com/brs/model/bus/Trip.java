package com.brs.model.bus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer fare;

    @NotNull
    private Date journeyTime;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    private Stop sourceStop;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private Stop destStop;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

    public Trip() {
    }

    public Trip(@NotNull Integer fare, @NotNull Date journeyTime, @NotNull Stop sourceStop, @NotNull Stop destStop,
                @NotNull Bus bus, @NotNull Agency agency) {
        this.fare = fare;
        this.journeyTime = journeyTime;
        this.sourceStop = sourceStop;
        this.destStop = destStop;
        this.bus = bus;
        this.agency = agency;
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

    public Stop getSourceStop() {
        return sourceStop;
    }

    public void setSourceStop(Stop sourceStop) {
        this.sourceStop = sourceStop;
    }

    public Stop getDestStop() {
        return destStop;
    }

    public void setDestStop(Stop destStop) {
        this.destStop = destStop;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id) &&
                Objects.equals(fare, trip.fare) &&
                Objects.equals(journeyTime, trip.journeyTime) &&
                Objects.equals(sourceStop, trip.sourceStop) &&
                Objects.equals(destStop, trip.destStop) &&
                Objects.equals(bus, trip.bus) &&
                Objects.equals(agency, trip.agency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fare, journeyTime, sourceStop, destStop, bus, agency);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", fare=" + fare +
                ", journeyTime=" + journeyTime +
                ", sourceStop=" + sourceStop +
                ", destStop=" + destStop +
                ", bus=" + bus +
                ", agency=" + agency +
                '}';
    }
}
