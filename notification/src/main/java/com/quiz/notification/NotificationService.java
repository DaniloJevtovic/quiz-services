package com.quiz.notification;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Page<Notification> getAll(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    public Notification getById(String id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Page<Notification> getAllNotificationsForUser(Integer userId, Pageable pageable) {
        return notificationRepository.findByReciverId(userId, pageable);
    }

    public List<Notification> getUnreadNotificationSforUser(Integer userId) {
        return notificationRepository.findByReciverIdAndReadFalse(userId);
    }

    public Notification save(NotificationDTO dto) {
        Notification notification = new Notification();
        notification.setNotification(dto.notification());
        notification.setReciverId(dto.reciverId());
        notification.setDate(LocalDateTime.now());
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    public void readNotification(String notificationId) {
        Notification notification = getById(notificationId);
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }
}
