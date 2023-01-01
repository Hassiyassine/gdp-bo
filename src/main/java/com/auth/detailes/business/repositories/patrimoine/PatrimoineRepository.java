package com.auth.detailes.business.repositories.patrimoine;

import java.util.List;

import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.utilities.enums.LandReserveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PatrimoineRepository extends JpaRepository<Patrimoine, Long>, JpaSpecificationExecutor<Patrimoine> {

	  List<Patrimoine> findByNameContaining(String name);
	  List<Patrimoine> findByNameContainingAndStatus(String name, LandReserveStatus status);
      boolean existsByName(String code);

    List<Patrimoine> findAllByCreatedBy(Long id);

    List<Patrimoine> findAllByIsNotified(Boolean isNotified);
}
