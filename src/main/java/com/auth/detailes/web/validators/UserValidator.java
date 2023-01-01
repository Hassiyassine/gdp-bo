package com.auth.detailes.web.validators;

import com.auth.detailes.web.dto.UtilisateurDTO;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;



@Component
public class UserValidator {


    public List<String> isValidCreateAccount(UtilisateurDTO dto){
        List<String> messages = new ArrayList<>();

        if (dto.getUserName() == null && dto.getUserName().isEmpty()){
            messages.add("UsernameRequired");
        }
        if (dto.getEmail() == null  && dto.getEmail().isEmpty()){
            messages.add("EmailRequired");
        }
        if (dto.getPassword() == null  && dto.getPassword().isEmpty()){
            messages.add("PasswordRequired");
        }else {
            if (dto.getPassword().length()<8){
                messages.add("PasswordSizeNotValid");
            }
        }
        return  messages;
    }

}
