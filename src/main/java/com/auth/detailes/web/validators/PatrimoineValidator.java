package com.auth.detailes.web.validators;

import com.auth.detailes.web.dto.PatrimoineDTO;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class PatrimoineValidator {


    public List<String> isValid(PatrimoineDTO dto){
        List<String> messages = new ArrayList<>();

        if (dto.getName() == null || dto.getName().isEmpty()){
            messages.add("NameRequired");
        }
        if (dto.getDateHomologation() == null  || dto.getDateHomologation().isEmpty()){
            messages.add("DateHomologationRequired");
        }
        if (dto.getDateHomologationExpiration() == null  || dto.getDateHomologationExpiration().isEmpty()){
            messages.add("DateHomologationExpirationRequired");
        }
        if (dto.getNumero() == null || dto.getNumero().isEmpty()){
            messages.add("NumeroValid");
        }
        return  messages;
    }

}
