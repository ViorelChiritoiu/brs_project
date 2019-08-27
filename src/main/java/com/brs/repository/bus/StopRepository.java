package com.brs.repository.bus;

import com.brs.model.bus.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {
    Stop findByCode(String code);
}
