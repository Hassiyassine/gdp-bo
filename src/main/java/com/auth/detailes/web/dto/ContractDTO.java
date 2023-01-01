package com.auth.detailes.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDTO {

    private Long id;
    private DocumentDTO document;
    private String dateStart;
    private String dateEnd;
    private String proprietaire;
    private String tauxaugmentation;
    private String delaisAugmentation;
    private String locataire;
    private String loyer;
    private String dateButtoire;
}
