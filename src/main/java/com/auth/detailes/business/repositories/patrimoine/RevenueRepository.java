package com.auth.detailes.business.repositories.patrimoine;

import com.auth.detailes.business.entites.patrimoine.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    List<Revenue> findRevenuesByNameContaining(String name);
}
