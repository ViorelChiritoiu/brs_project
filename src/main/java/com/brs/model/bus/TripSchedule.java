package com.brs.model.bus;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class TripSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Trip detail;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> ticketsSold;

    @NotNull
    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private Date tripDate;

    @NotNull
    private Integer availableSeats;

    public TripSchedule() {
    }

    public TripSchedule(@NotNull Trip detail, @NotNull List<Ticket> ticketsSold,
                        @NotNull @FutureOrPresent Date tripDate, @NotNull Integer availableSeats) {
        this.detail = detail;
        this.ticketsSold = ticketsSold;
        this.tripDate = tripDate;
        this.availableSeats = availableSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getDetail() {
        return detail;
    }

    public void setDetail(Trip detail) {
        this.detail = detail;
    }

    public List<Ticket> getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(List<Ticket> ticketsSold) {
        this.ticketsSold = ticketsSold;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripSchedule that = (TripSchedule) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(detail, that.detail) &&
                Objects.equals(ticketsSold, that.ticketsSold) &&
                Objects.equals(tripDate, that.tripDate) &&
                Objects.equals(availableSeats, that.availableSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, detail, ticketsSold, tripDate, availableSeats);
    }

    @Override
    public String toString() {
        return "TripSchedule{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", ticketsSold=" + ticketsSold +
                ", tripDate=" + tripDate +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
