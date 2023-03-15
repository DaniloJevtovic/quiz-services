package com.quiz.notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    Page<Notification> findByReciverId(Integer reciverId, Pageable pageable);

    // neprocitane
    List<Notification> findByReciverIdAndReadFalse(Integer reciverId);
}
