package com.auth.detailes.business.entites.auth;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.auth.detailes.business.entites.common.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 	: Yassine HASSI
 * @version : 1.0.0
 * Email	: yassine.ismail.hassi@gmail.com
 * Date	    : Apr 25, 2020
 */

@Entity
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role  extends UserDateAudit {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "label", nullable = false)
	private String label;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "description", nullable = true,columnDefinition = "text")
	private String description;
	
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();
    
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToMany(mappedBy = "role")
    private Set<User> users;
}
