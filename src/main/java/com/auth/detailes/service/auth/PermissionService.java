package com.auth.detailes.service.auth;

import com.auth.detailes.business.entites.auth.Authority;
import com.auth.detailes.service.mpl.GenericService;

import java.util.List;

public interface PermissionService  extends GenericService<Authority> {

    List<Authority> findAllList();

}
