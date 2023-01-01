package com.auth.detailes.business.repositories.patrimoine;

import com.auth.detailes.business.entites.patrimoine.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>{
}
