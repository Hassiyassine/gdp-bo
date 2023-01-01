package com.auth.detailes.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorityDTO {

    private Long id;

    private String libelle;

    private String codeAuthority;

    private String roleDescription;
}
