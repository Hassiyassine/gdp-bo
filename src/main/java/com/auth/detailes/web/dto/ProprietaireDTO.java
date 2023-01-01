package com.auth.detailes.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProprietaireDTO extends UserDateAuditDTO{

    private Long  id;

    private String  uid;

    private  String label;

    private  String code;

    private String description;

}
