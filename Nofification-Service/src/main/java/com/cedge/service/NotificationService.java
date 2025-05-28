package com.cedge.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cedge.model.Notification;
import com.cedge.repo.NotificationRepository;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class NotificationService {

	private final NotificationRepository notificationRepository;

	public NotificationService(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	public Notification sendNotification(String recipient, String message, String type) {
		// Business logic for sending notifications
		// For simplicity, we'll just log the notification as "SENT"
		Notification notification = new Notification();
		notification.setRecipient(recipient);
		notification.setMessage(message);
		notification.setType(type);
		notification.setStatus("SENT");
		notification.setSentAt(LocalDateTime.now());

		// Save to repository
		return notificationRepository.save(notification);
	}
	
	@RateLimiter(name = "notificationServiceRL", fallbackMethod = "fallbackForNotification")
    public String sendNotification(String message) {
        // Simulate sending a notification
        return "Notification Sent: " + message;
    }

    public String fallbackForNotification(String message, Throwable throwable) {
        return "Rate limit exceeded. Unable to send notification at the moment.";
    }

}
