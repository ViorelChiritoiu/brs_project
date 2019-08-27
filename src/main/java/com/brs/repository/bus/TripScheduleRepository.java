package com.brs.repository.bus;

import com.brs.model.bus.Trip;
import com.brs.model.bus.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
    TripSchedule findByDetailAndTripDate(Trip detail, Date tripDate);
}
