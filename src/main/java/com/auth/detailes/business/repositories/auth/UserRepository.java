package com.auth.detailes.business.repositories.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.auth.detailes.business.entites.auth.User;


/**
 * @author 	: Yassine HASSI
 * @version : 1.0.0
 * Email	: yassine.ismail.hassi@gmail.com
 * Date	    : Apr 25, 2020
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

	Optional<User> findByUserName(String username);
	boolean existsByUserName(String username);
	Optional<User> findOneByUserNameAndEmail(String username,String email);
}
