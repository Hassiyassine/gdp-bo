package com.auth.detailes.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSearchRequest extends SearchRequest {

	private String typeRegimCode;
	private String typeBienCode;
	private String name;
	private String numero;
	private String ville;
}

