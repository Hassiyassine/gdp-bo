package com.auth.detailes.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTO extends  UserDateAuditDTO{

    public Long id;
    private String libelle;
    private String description;
    private String path;
    private String filename;
    private String img;
    private String uniqueCode;
    private Boolean isPrimary;
    private TypeDocumentDTO typeDocument;
    private String dateDel;
    private String date_caducit;

}
