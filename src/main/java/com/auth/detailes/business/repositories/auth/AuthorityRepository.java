package com.auth.detailes.business.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.detailes.business.entites.auth.Authority;

/**
 * @author 	: Yassine HASSI
 * @version : 1.0.0
 * Email	: yassine.ismail.hassi@gmail.com
 * Date	    : Apr 25, 2020
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
