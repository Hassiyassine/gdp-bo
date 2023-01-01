package com.auth.detailes.business.repositories.referentiel;

import com.auth.detailes.business.entites.patrimoine.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> , JpaSpecificationExecutor<Tag> {
}
