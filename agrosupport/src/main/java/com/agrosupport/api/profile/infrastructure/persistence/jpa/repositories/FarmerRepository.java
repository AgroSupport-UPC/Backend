package com.agrosupport.api.profile.infrastructure.persistence.jpa.repositories;

import com.agrosupport.api.profile.domain.model.entities.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    Optional<Farmer> findByUser_Id(Long userId);
}
