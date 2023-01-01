package com.auth.detailes.web.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class RoleDTO {

    private Long id;

    private String libelle;

    private String code;

    private String description;

    private List<AuthorityDTO> authoritys;

    private List<String> permissions;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;
}
