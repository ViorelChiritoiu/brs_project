package com.brs.model.bus;

import com.brs.model.user.UserAccount;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer seatNumber;

    @NotNull
    private boolean cancellable;

    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private Date journeyDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripschedule_id", nullable = false)
    private TripSchedule tripSchedule;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private UserAccount passenger;

    public Ticket() {
    }

    public Ticket(@NotNull Integer seatNumber, @NotNull Boolean cancellable, @FutureOrPresent Date journeyDate,
                  @NotNull TripSchedule tripSchedule, @NotNull UserAccount passenger) {
        this.seatNumber = seatNumber;
        this.cancellable = cancellable;
        this.journeyDate = journeyDate;
        this.tripSchedule = tripSchedule;
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
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

    public TripSchedule getTripSchedule() {
        return tripSchedule;
    }

    public void setTripSchedule(TripSchedule tripSchedule) {
        this.tripSchedule = tripSchedule;
    }

    public UserAccount getPassenger() {
        return passenger;
    }

    public void setPassenger(UserAccount passenger) {
        this.passenger = passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(seatNumber, ticket.seatNumber) &&
                Objects.equals(cancellable, ticket.cancellable) &&
                Objects.equals(journeyDate, ticket.journeyDate) &&
                Objects.equals(tripSchedule, ticket.tripSchedule) &&
                Objects.equals(passenger, ticket.passenger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatNumber, cancellable, journeyDate, tripSchedule, passenger);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", cancellable=" + cancellable +
                ", journeyDate=" + journeyDate +
                ", tripSchedule=" + tripSchedule +
                ", passenger=" + passenger +
                '}';
    }
}
