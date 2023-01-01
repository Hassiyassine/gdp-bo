package com.auth.detailes.web.requests;

import lombok.Data;
import java.io.Serializable;

@Data
public class ResetPasswordRequest implements Serializable {

    private String login;
    private String email;
    private String resetPassword;

}
