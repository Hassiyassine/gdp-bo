package com.auth.detailes.business.repositories.patrimoine;

import com.auth.detailes.business.entites.patrimoine.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> , JpaSpecificationExecutor<Notification> {
    Optional<Notification> findByPatrimoineId(Long id);
}
