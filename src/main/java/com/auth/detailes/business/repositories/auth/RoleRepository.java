package com.auth.detailes.business.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.auth.detailes.business.entites.auth.Role;

import java.util.Optional;

/**
 * @author 	: Yassine HASSI
 * @version : 1.0.0
 * Email	: yassine.ismail.hassi@gmail.com
 * Date	    : Apr 25, 2020
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    boolean existsByCode(String code);

    Optional<Role> findByCode(String role_user);
}
