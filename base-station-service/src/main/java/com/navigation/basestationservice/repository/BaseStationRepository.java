package com.navigation.basestationservice.repository;


import com.navigation.basestationservice.model.BaseStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseStationRepository extends JpaRepository<BaseStation, Long> {
}
