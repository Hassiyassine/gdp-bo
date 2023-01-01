package com.auth.detailes.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 	: Yassin OUICHOU | Ouichou.IT@gmail.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest extends SearchRequest {

	@JsonProperty
	private String lastname;
	@JsonProperty
	private String firstname;
	@JsonProperty
	private String userName;
	@JsonProperty
	private String login;
	@JsonProperty
	private String email;
	@JsonProperty
	private Boolean enabled;
	@JsonProperty
	private String refRoleCode;
	@JsonProperty
	private String libelle;
	@JsonProperty
	private String membre;
}
