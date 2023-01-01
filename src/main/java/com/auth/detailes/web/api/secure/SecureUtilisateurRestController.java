package com.auth.detailes.web.api.secure;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.service.IUtilisateurService;
import com.auth.detailes.service.mailer.ApplicationMailerTemplate;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.DefaultCrudOperations;
import com.auth.detailes.web.common.EndPointConstent;
import com.auth.detailes.web.dto.UtilisateurDTO;
import com.auth.detailes.web.mappers.UtilisateurMapper;
import com.auth.detailes.web.requests.ChangePassword;
import com.auth.detailes.web.requests.UserSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping(EndPointConstent.SECURE_UTILISATEUR_ENDPOINT)
@AllArgsConstructor
public class SecureUtilisateurRestController extends CustomRestController implements DefaultCrudOperations<UtilisateurDTO, UserSearchRequest> {
	
	private IUtilisateurService service;
	private UtilisateurMapper mapper;
	private ApplicationMailerTemplate applicationMailerTemplate;
	// COMMON FUNCTIONS
	@Override
	public ApiResponse<Page<?>> findAllByCriteria(UserSearchRequest searchRequest){
		Page<User> page = this.service.findAll(searchRequest, PageRequest.of(searchRequest.getPage(), searchRequest.getSize()));
		return new ApiResponse<>(Boolean.TRUE, null, page.map(mapper::toDTO),null);
	}

	@Override
	public ApiResponse<UtilisateurDTO> findOneById(@PathVariable Long id){
	Optional<User> opt = this.service.find(id);
		if(opt.isPresent()){
			return new ApiResponse<>(Boolean.TRUE, null, mapper.toDTO(opt.get()),null);
		}
		return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UtilisateurNotFound"), null,null);
	}

	@Override
	public ApiResponse<Boolean> exists(String code) {
		if(code != null && !code.isEmpty())
			return new ApiResponse<>(Boolean.TRUE, null, this.service.existsByCode(code),null);
		return new ApiResponse<>(Boolean.FALSE, Arrays.asList("USER_NOT_CONNECTED"), null,null);
	}

	@Override
	public ApiResponse<UtilisateurDTO> add(@RequestBody  UtilisateurDTO formulaire){
		try{
			User entity = this.service.init(formulaire);
			return new ApiResponse<>(Boolean.TRUE, null, mapper.toDTO(entity),null);
		}catch (Exception e){
			e.printStackTrace();
			return new ApiResponse<>(Boolean.FALSE, Arrays.asList("LoginAlreadyExists"), null,null);
		}
	}

	@Override
	public ApiResponse<UtilisateurDTO> update(@PathVariable Long id, @RequestBody UtilisateurDTO formulaire) throws ConfigDataResourceNotFoundException {
		Optional<User> opt = this.service.find(id);
		if(opt.isPresent()){
			if(!formulaire.isPasswordSave()){
				formulaire.setPassword(null);
			}
			User entity = mapper.toEntity(formulaire);
			entity = this.service.update(id, entity);


			if(formulaire.isPasswordNotifyUser() && formulaire.getPassword()!=null) {
				applicationMailerTemplate.notifyPasswordChanged(
						entity.getFirstName() + " " + entity.getLastName(),
						entity.getUsername(),
						entity.getEmail(),
						formulaire.getPassword(),
						"Notification changement de mot de passe"
				);
			}

			return new ApiResponse<>(Boolean.TRUE, null, mapper.toDTO(entity),null);
		}else{
			return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UserNotFound"), null,null);
		}
	}

	@Override
	public ApiResponse<Boolean> delete(@PathVariable Long id)  {
	Optional<User> opt = this.service.find(id);
		if(opt.isPresent()){
			boolean isDeleted = this.service.delete(opt.get());
			if(isDeleted){
				return new ApiResponse<>(Boolean.TRUE, null, Boolean.TRUE,null);
			}
		}
		return new ApiResponse<>(Boolean.FALSE, Arrays.asList("CantDeleteElement"), null,null);
	}


	@GetMapping("/current")
	public ApiResponse<UtilisateurDTO> fetchUser(Principal userDetails) {
		if(userDetails.getName() != null && !userDetails.getName().isEmpty()){
			return new ApiResponse<>(Boolean.TRUE, null,mapper.toDTO(service.findAccountByLogin(userDetails.getName()).get()),null);
		}
		return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UserNotFound"), null,null);
	}

	@PostMapping("/avatar")
	public ApiResponse<UtilisateurDTO> fetchUser(Principal userDetails, MultipartFile file) {
		if(userDetails.getName() != null && !userDetails.getName().isEmpty()){
		User user=this.service.uploadAvatar(userDetails.getName(),file);
		if(user!=null){
			return new ApiResponse<>(Boolean.TRUE, null,mapper.toDTO(service.findAccountByLogin(userDetails.getName()).get()),null);
		}
			return new ApiResponse<>(Boolean.FALSE,Arrays.asList("CantUpload") ,null,null);
		}
		return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UserNotFound"), null,null);
	}

	@PostMapping("/check")
	public ApiResponse<Boolean> checkPassword(@RequestBody ChangePassword pwd, Principal userDetails){
		if (this.service.changePassword(pwd, userDetails.getName())){
			return new ApiResponse<>(Boolean.TRUE, null,true,null);
		}
		return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UserNotFound"), null,null);
	}

	@PostMapping("/profile")
	public ApiResponse<Boolean> checkPassword(@RequestBody UtilisateurDTO dto, Principal userDetails){
		if (this.service.profile(mapper.toEntity(dto), userDetails.getName())){
			return new ApiResponse<>(Boolean.TRUE, null,true,null);
		}
		return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UserNotFound"), null,null);
	}

}
