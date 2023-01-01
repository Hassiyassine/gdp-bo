package com.auth.detailes.business.repositories.patrimoine;

import com.auth.detailes.business.entites.patrimoine.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

}
