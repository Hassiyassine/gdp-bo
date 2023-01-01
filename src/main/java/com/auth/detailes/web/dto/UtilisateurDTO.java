package com.auth.detailes.web.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;


@Data
@Builder
public class UtilisateurDTO  {

    private Long id;
    private String lastName;
    private String firstName;
    private String userName;
    private String email;
    private String password;
    private boolean enable;
    private boolean firstLogin;
    private String avatar;
    private RoleDTO role;
    private List<String> permissions;
    private String description;
    private String fonction;
    private String  service;
    private boolean passwordSave = false;
    private String  createdAt;
    private String updatedAt;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String personalPhone;
    private String professtionalPhone;
    private boolean passwordNotifyUser = false;
    private String addressIp;
}
