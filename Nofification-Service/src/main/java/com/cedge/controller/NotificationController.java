package com.cedge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cedge.model.Notification;
import com.cedge.model.NotificationModel;
import com.cedge.service.NotificationService;

@RestController
@RequestMapping("notifications")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@PostMapping("/send")
	public ResponseEntity<Notification> sendNotification(/*
															 * @RequestParam String recipient, @RequestParam String
															 * message,
															 * 
															 * @RequestParam String type
															 */  @RequestBody NotificationModel notification) {
		
		String recipient = notification.getRecipient();
		String message = notification.getMessage();
		String type = notification.getType();
		Notification notification1 = notificationService.sendNotification(recipient, message, type);
		return new ResponseEntity<>(notification1, HttpStatus.CREATED);
	}

}
