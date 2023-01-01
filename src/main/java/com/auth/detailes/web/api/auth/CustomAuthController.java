package com.auth.detailes.web.api.auth;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.auth.detailes.service.IUtilisateurService;
import com.auth.detailes.web.common.EndPointConstent;
import com.auth.detailes.web.dto.UtilisateurDTO;
import com.auth.detailes.web.mappers.UtilisateurMapper;
import com.auth.detailes.web.validators.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth.detailes.confg.CostomJwtHelper;
import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.requests.SearchRequestLogin;
import com.auth.detailes.web.responses.ResponseLogin;


@RestController
@RequestMapping(EndPointConstent.PUBLIC_END_POINT)
@Slf4j @AllArgsConstructor
public class CustomAuthController {

	private CostomJwtHelper jwtAuthentication;
	private AuthenticationManager authenticationManager;
	private IUtilisateurService service;
	private UtilisateurMapper mapper;
	private UserValidator validator;

	@PostMapping("/login")
	public ApiResponse<?> login(@RequestBody SearchRequestLogin search) throws InvalidKeySpecException, NoSuchAlgorithmException{
		try {
			Optional<User> userRequest= this.service.findAccountByLogin(search.getUserName());
			if(userRequest.isPresent()){
				if (userRequest.get().isEnable()){
					Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
							search.getUserName(), search.getPassword()));
					SecurityContextHolder.getContext().setAuthentication(auth);
					User user = (User) auth.getPrincipal();
					ResponseLogin response = new ResponseLogin();
					response.setToken(jwtAuthentication.generateToken(user.getUsername()));
					return new ApiResponse<ResponseLogin>(Boolean.TRUE,null,response,"Login");
				}
				return new ApiResponse<ResponseLogin>(Boolean.FALSE, Collections.singletonList("ce compte est désactivé"), null,"Login");
			}
			return new ApiResponse<ResponseLogin>(Boolean.FALSE, Collections.singletonList("nom d'utilisateur non trouvé"), null,"Login");
		} catch (Exception e){
		log.error("Error login : {} ",e.getMessage());
		}
		return new ApiResponse<>(Boolean.FALSE, Collections.singletonList("Mots de passe incorrect"),null,"Login");
	}

	@PostMapping("/register")
	public ApiResponse<UtilisateurDTO> createAccount(@RequestBody  UtilisateurDTO form){
			Optional<User> optional = this.service.findAccountByLogin(form.getUserName());
			if(!optional.isPresent()){
				List<String> messages = this.validator.isValidCreateAccount(form);
				if(messages.isEmpty()){
					User entity = this.service.init(form);
					return new ApiResponse<>(Boolean.TRUE, null, mapper.toDTO(entity),null);
				}
				return new ApiResponse<>(Boolean.FALSE, messages, null,null);
			}
			return new ApiResponse<>(Boolean.FALSE, Collections.singletonList("LoginAlreadyExists"), null,null);
	}
	
}
