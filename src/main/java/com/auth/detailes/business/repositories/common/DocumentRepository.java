package com.auth.detailes.business.repositories.common;

import com.auth.detailes.business.entites.common.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> , JpaSpecificationExecutor<Document> {
    Optional<Document> findByUniqueCode(String uniqueCode);
    Optional<Document> findTopByUniqueCodeOrderByIdDesc(String uniqueCode);
}
