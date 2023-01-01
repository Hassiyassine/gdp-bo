package com.auth.detailes.business.repositories.referentiel;

import com.auth.detailes.business.entites.referentiel.NatureJuridique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NatureJuridiqueRepository extends JpaRepository<NatureJuridique,Long> , JpaSpecificationExecutor<NatureJuridique> {
}
