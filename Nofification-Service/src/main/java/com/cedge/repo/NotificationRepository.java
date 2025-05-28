package com.cedge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cedge.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
}
