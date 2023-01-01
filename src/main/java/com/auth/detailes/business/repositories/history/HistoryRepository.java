package com.auth.detailes.business.repositories.history;


import com.auth.detailes.business.entites.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoryRepository extends JpaRepository<History,Long> , JpaSpecificationExecutor<History> {
}
