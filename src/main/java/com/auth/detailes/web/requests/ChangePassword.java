package com.auth.detailes.web.requests;

import lombok.Data;

@Data
public class ChangePassword {

    private  String lastPassword;
    private String newPassword;

}
