package com.auth.detailes.web.validators;

import com.auth.detailes.web.requests.ResetPasswordRequest;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResetPasswordValidator {

    public List<String> isValid(ResetPasswordRequest request){
        List<String> messages = new ArrayList<>();

        if (request.getLogin() == null && request.getLogin().isEmpty()){
            messages.add("ResetPasswordLoginRequired");
        }
        if (request.getEmail() == null  && request.getEmail().isEmpty()){
            messages.add("ResetPasswordEmailRequired");
        }
        return  messages;
    }

}
