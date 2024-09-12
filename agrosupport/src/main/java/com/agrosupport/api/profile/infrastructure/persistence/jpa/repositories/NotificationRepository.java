package com.agrosupport.api.profile.infrastructure.persistence.jpa.repositories;

import com.agrosupport.api.profile.domain.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser_Id(Long userId);
}
