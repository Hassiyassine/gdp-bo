package com.auth.detailes.business.entites.auth;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;
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
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

 	@Column(name = "label", nullable = false)
	private String label;
	
	@Column(name = "authorityCode", nullable = false)
	private String codeAuthority;
	
	@Column(name = "description", nullable = true,columnDefinition = "text")
	private String description;
	
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
	
	@Override
	public String getAuthority() {
		return this.codeAuthority;
	}

}
