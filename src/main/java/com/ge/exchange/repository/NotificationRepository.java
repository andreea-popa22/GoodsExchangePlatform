package com.ge.exchange.repository;

import com.ge.exchange.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query("select n from Notification n where n.user.userId = :userId")
    List<Notification> getNotificationsForUser(int userId);
}
