package com.auth.detailes.service.mpl;


import com.auth.detailes.business.entites.patrimoine.Notification;
import java.util.List;
import java.util.Optional;


public interface NotificationService extends GenericService<Notification> {
    List<Notification> findAll();
    Optional<Notification> findByPatrimoineId(Long id);
}
