package com.auth.detailes.web.api.secure.statistique;


import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.service.IUtilisateurService;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.EndPointConstent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(EndPointConstent.SECURE_STATISTIQUE_ENDPOINT)
@AllArgsConstructor
@Slf4j
public class CustomStatistiqueRestController extends CustomRestController {


    private final IUtilisateurService service;


    @GetMapping("/produit-count")
    public ApiResponse<?> countProduit(Principal principal){
        String userName = principal.getName();
        Optional<User> user = this.service.findAccountByLogin(userName);
        if(user.isPresent()){
            System.err.println(user.get().getRole().getAuthorities().size());
            if(user.get().getRole().getAuthorities().size() >= 8){
                return  new ApiResponse<>(Boolean.TRUE,null,"this is admin",null);
            }
            return  new ApiResponse<>(Boolean.TRUE,null,"this is user",null);
        }
        return  new ApiResponse<>(Boolean.FALSE,null,null,null);
    }

}
