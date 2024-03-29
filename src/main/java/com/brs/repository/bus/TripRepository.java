package com.brs.repository.bus;

import com.brs.model.bus.Agency;
import com.brs.model.bus.Bus;
import com.brs.model.bus.Stop;
import com.brs.model.bus.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findBySourceStopAndDestStopAndBus(Stop source, Stop dest, Bus bus);
    List<Trip> findAllBySourceStopAndDestStop(Stop source, Stop dest);
    List<Trip> findByAgency(Agency agency);
}
