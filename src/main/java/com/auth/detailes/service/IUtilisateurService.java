package com.auth.detailes.service;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.service.mpl.GenericService;
import com.auth.detailes.web.dto.UtilisateurDTO;
import com.auth.detailes.web.requests.ChangePassword;
import com.auth.detailes.web.requests.ResetPasswordRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;



public interface IUtilisateurService extends GenericService<User> {

    Optional<User> findAccountByLogin(String login);

    User init(UtilisateurDTO formulaire);

    Boolean changePassword(ChangePassword pwd, String userName);

    Boolean profile(User entity, String username);

    User resetPassword(ResetPasswordRequest request);

    User uploadAvatar(String username, MultipartFile file);


}
