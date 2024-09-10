package com.agrosupport.api.profile.infrastructure.persistence.jpa.repositories;

import com.agrosupport.api.profile.domain.model.entities.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    Optional<Advisor> findByUser_Id(Long userId);
}
