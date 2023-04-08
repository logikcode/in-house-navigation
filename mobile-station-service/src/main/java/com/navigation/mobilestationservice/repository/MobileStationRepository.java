package com.navigation.mobilestationservice.repository;

import com.navigation.mobilestationservice.model.MobileStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileStationRepository extends JpaRepository<MobileStation, Long> {
}
