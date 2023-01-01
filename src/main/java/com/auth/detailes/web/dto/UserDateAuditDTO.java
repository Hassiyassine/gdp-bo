package com.auth.detailes.web.dto;


import lombok.Data;

@Data
public class UserDateAuditDTO {

    private Long createdBy;
    private Long updatedBy;
    private String createdAt;
    private String updatedAt;


}
