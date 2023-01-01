package com.auth.detailes.web.responses;

import com.auth.detailes.utilities.enums.Status;
import com.auth.detailes.web.dto.*;
import com.auth.detailes.web.dto.geojson.GeometryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class PatrimoineResponse extends UserDateAuditDTO {

	private Long id;
	private String name;
	private ProprietaireDTO proprietaire;
	private TypeBienDTO typeBien;
	private TypeRegimDTO typeRegim;
	private String numero;
	private String superficie;
	private String utilisation;
	private String ville;
	private String adresse;
	private String affectation;
	private String dateHomologation;
	private String dateHomologationExpiration;
	private String detailsApposition;
	private String description;
 	private Status status;
	private String address;

	private String imageCode;
}
