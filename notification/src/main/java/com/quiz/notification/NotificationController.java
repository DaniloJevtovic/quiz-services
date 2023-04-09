package com.quiz.notification;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public Page<Notification> getAllNotification(Pageable pageable) {
        return notificationService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Notification getNotificatinById(@PathVariable String id) {
        return notificationService.getById(id);
    }

    @GetMapping("/reciver/{userId}")
    public Page<Notification> getAllNotificationForUser(@PathVariable Integer userId, Pageable pageable) {
        return notificationService.getAllNotificationsForUser(userId, pageable);
    }

    @GetMapping("/reciver/{userId}/unread")
    public List<Notification> getUnreadNotificationsForUser(@PathVariable Integer userId) {
        return notificationService.getUnreadNotificationsForUser(userId);
    }

    @PostMapping
    public Notification sentNotification(@RequestBody NotificationDTO dto) {
        return notificationService.sendNotification(dto);
    }

    @PatchMapping("/{id}")
    public void readNotification(@PathVariable String id) {
        notificationService.readNotification(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }

    @DeleteMapping("/all")
    public void deleteAllNotifications() {
        notificationService.deleteAll();
    }
}
