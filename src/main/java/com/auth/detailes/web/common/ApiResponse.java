package com.auth.detailes.web.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 	: Yassine HASSI
 * @version : 1.0.0
 * Email	: yassine.ismail.hassi@gmail.com
 * Date	    : Apr 25, 2020
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {

	private boolean success;
	private List<String> messages;
	private T data;
	private String entitieName=null;
	
	
}
