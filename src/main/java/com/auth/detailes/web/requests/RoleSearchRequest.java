package com.auth.detailes.web.requests;

import lombok.Data;

@Data
public class RoleSearchRequest extends SearchRequest{

    private String libelle;

    private String code;

    private String description;
}
