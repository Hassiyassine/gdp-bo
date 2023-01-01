package com.auth.detailes.service.mpl;


import com.auth.detailes.business.entites.patrimoine.Patrimoine;

import java.util.List;

public interface PatrimoineService extends GenericService<Patrimoine> {

    List<Patrimoine> findAllByCreatedBy(Long id);
    List<Patrimoine> findAll(Boolean isNotified);
    Long count();
    Long countByUserId(Long id);
    Boolean archived(Patrimoine current);
}
