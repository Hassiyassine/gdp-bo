package com.auth.detailes.web.api.auth;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.service.IUtilisateurService;
import com.auth.detailes.service.mailer.ApplicationMailerTemplate;
import com.auth.detailes.utilities.StringGeneratorCode;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.EndPointConstent;
import com.auth.detailes.web.requests.ResetPasswordRequest;
import com.auth.detailes.web.validators.ResetPasswordValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPointConstent.PUBLIC_END_POINT)
@Slf4j
@AllArgsConstructor
public class ResetPasswordRestController extends CustomRestController {

    private final IUtilisateurService service;
    private final ApplicationMailerTemplate applicationMailerTemplate;
    private final ResetPasswordValidator validator;

    @PostMapping("/reset-password")
    public ApiResponse<Boolean> resetPassword(@RequestBody ResetPasswordRequest request){
        List<String> messages = validator.isValid(request);
        if(messages.isEmpty())
        {   request.setResetPassword(StringGeneratorCode.generateRandomString(8));
            User utilisateur = this.service.resetPassword(request);
            if(utilisateur!=null){
                try {
                this.applicationMailerTemplate.notifyPasswordChanged(
                        utilisateur.getFirstName()+" "+ utilisateur.getLastName()
                        ,utilisateur.getUsername(),utilisateur.getEmail(),request.getResetPassword(),
                        "Notification de récupération de mot de passe"
                );
                log.error("Email send for user  {}",utilisateur.getFirstName()+" "+ utilisateur.getLastName());
                messages.add("EMAILHASBEENSENDED");
                }catch(Exception e){
                    log.error("cant to send  mail to user  error message : {}",e.getMessage());
                    messages.add("EMAILNOTSENDED");
                    return  new ApiResponse<>(Boolean.FALSE, messages,null,null);
                }
                return  new ApiResponse<>(Boolean.TRUE, messages,true,null);
            }
            messages.add("UtilisateurNotFound");
            return  new ApiResponse<>(Boolean.FALSE, messages,null,null);
        }
        return  new ApiResponse<>(Boolean.FALSE,messages,null,null);
    }


    @GetMapping("/test")
    public String ss(){

        return  "test";
    }

}
