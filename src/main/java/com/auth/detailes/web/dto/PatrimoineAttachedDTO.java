package com.auth.detailes.web.dto;


import com.auth.detailes.web.responses.PatrimoineResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class PatrimoineAttachedDTO extends UserDateAuditDTO{

	private Long id;
	private String label;
	private PatrimoineResponse patrimoineAttached;
	private TypeRelationDTO typeRelation;
}
