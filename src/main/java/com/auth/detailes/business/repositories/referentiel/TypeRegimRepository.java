package com.auth.detailes.business.repositories.referentiel;

import com.auth.detailes.business.entites.referentiel.TypeRegim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRegimRepository extends JpaRepository<TypeRegim,Long> , JpaSpecificationExecutor<TypeRegim> {
}
