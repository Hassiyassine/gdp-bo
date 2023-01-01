package com.auth.detailes.business.entites.auth;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import com.auth.detailes.business.entites.common.UserDateAudit;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 	: Yassine HASSI
 * @version : 1.0.0
 * Email	: yassine.ismail.hassi@gmail.com
 * Date	    : Apr 25, 2020
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User extends UserDateAudit implements UserDetails  {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
	private Long id;
	@Column(name = "username", nullable = false, length = 30, unique = true)
	private String userName;

	@Column(name = "firstname", nullable = true)
	private String firstName;

	@Column(name = "lastname", nullable = true)
	private String lastName;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "avatar", nullable = true)
	private String avatar;

	@Column(name = "addressLine1", nullable = true)
	private String addressLine1;

	@Column(name = "addressLine2", nullable = true)
	private String addressLine2;

	@Column(name = "postalCode", nullable = true)
	private String postalCode;

	@Column(name = "personalPhone", nullable = true)
	private String personalPhone;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "fonction", nullable = true)
	private String fonction;

	@Column(name = "professtionalPhone", nullable = true)
	private String professtionalPhone;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled")
	private boolean enable;

	@Column(name = "firstLogin")
	private boolean firstLogin=false;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Role role;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>(this.getRole().getAuthorities());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
	return this.enable;
	}

	@Override
	public boolean isAccountNonLocked() {
	return this.enable;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	return this.enable;
	}

	@Override
	public boolean isEnabled() {
		return  this.enable;
	}

}
