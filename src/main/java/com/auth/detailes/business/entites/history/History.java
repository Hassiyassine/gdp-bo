package com.auth.detailes.business.entites.history;

import com.auth.detailes.utilities.enums.TypeHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
public class History {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

 	@Column(name = "typeHistory")
	private TypeHistory typeHistory;

	@Column(name = "description",columnDefinition = "text")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createAt")
	private Date createAt;

	@Column(name = "createBy")
	private Long createBy;

}
