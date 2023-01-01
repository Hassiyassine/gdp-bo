package com.auth.detailes.web.responses;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLogin implements  Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;

}
