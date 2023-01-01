package com.auth.detailes.web.dto;

import lombok.*;


@Data
@Builder
public class ReferentialDTO extends UserDateAuditDTO{

    private Long id;
    private String code;
    private String libelle;
    private String description;
    private String num;
}
