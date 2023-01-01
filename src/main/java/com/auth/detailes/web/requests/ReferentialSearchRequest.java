package com.auth.detailes.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferentialSearchRequest extends SearchRequest {

	@JsonProperty
	private Long id;
	private String description;
	private String libelle;
	private String code;
}

