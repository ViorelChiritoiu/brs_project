package com.brs.repository.bus;

import com.brs.model.bus.Agency;
import com.brs.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Agency findByCode(String code);
    Agency findByOwner(UserAccount owner);
    Agency findByName(String name);
}
