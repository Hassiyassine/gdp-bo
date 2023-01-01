package com.auth.detailes.business.repositories.referentiel;

import com.auth.detailes.business.entites.patrimoine.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire,Long> , JpaSpecificationExecutor<Proprietaire> {
}
