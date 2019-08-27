package com.brs.repository.bus;

import com.brs.model.bus.Agency;
import com.brs.model.bus.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findByCode(String code);
    Bus findByCodeAndAgency(String code, Agency agency);
}
