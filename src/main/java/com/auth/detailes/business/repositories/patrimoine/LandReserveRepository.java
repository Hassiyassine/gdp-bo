package com.auth.detailes.business.repositories.patrimoine;

import com.auth.detailes.business.entites.patrimoine.LandReserve;

import com.auth.detailes.utilities.enums.LandReserveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LandReserveRepository extends JpaRepository<LandReserve, Long> {

    List<LandReserve> findByNameContaining(String name);

    Optional<LandReserve> findByAddressContaining(String address);

    List<LandReserve> findAllByStatusAndNameContainingAndAddressContainingAndCityContaining(
            LandReserveStatus status, String name, String address, String city);

    List<LandReserve> findAllByNameContainingAndAddressContainingAndCityContaining(
            String name, String address, String city);
}
