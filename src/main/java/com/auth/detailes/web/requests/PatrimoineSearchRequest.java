package com.auth.detailes.web.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatrimoineSearchRequest extends SearchRequest {


	private String typeRegimCode;
	private String typeBienCode;
	private String name;
	private String numero;
	private String ville;
	private Boolean isNotifiedParent;
	private Boolean isNotifiedContract;
}

