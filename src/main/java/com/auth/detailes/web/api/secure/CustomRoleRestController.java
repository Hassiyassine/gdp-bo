package com.auth.detailes.web.api.secure;

import com.auth.detailes.business.entites.auth.Authority;
import com.auth.detailes.business.entites.auth.Role;
import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.repositories.auth.UserRepository;
import com.auth.detailes.service.auth.PermissionService;
import com.auth.detailes.service.auth.RoleService;
import com.auth.detailes.web.common.*;
import com.auth.detailes.web.dto.AuthorityDTO;
import com.auth.detailes.web.dto.RoleDTO;
import com.auth.detailes.web.mappers.AuthorityMapper;
import com.auth.detailes.web.mappers.RoleMapper;
import com.auth.detailes.web.requests.RoleSearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(EndPointConstent.ROLES_ENDPOINT_REST)
public class CustomRoleRestController extends CustomRestController implements DefaultCrudOperationsPrincipal<RoleDTO, RoleSearchRequest> {

    private final RoleService service;
    private  final PermissionService permissionService;
    private  final AuthorityMapper authorityMapper;
    private  final RoleMapper mapper;
    private  final UserRepository repository;

    @Override
    public ApiResponse<Page<?>> findAllByCriteria(RoleSearchRequest searchRequest, Principal principal) {
        Page<Role> page = this.service.findAll(searchRequest, PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
        return new ApiResponse<>(Boolean.TRUE,null,page.map(mapper::toDTO),null);
    }

    @Override
    public ApiResponse<RoleDTO> findOneById(Long id, Principal principal) {
        return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(this.service.find(id).get()),null);
    }

    @Override
    public ApiResponse<Boolean> exists(String code, Principal principal) {
        return new ApiResponse<>(Boolean.TRUE,null,this.service.existsByCode(code),null);
    }

    @Override
    public ApiResponse<RoleDTO> add(RoleDTO form, Principal principal) {
        User user = this.repository.findByUserName(principal.getName()).get();
        if(user!=null) {
            Date date = new Date();
            form.setCreatedAt(date);
            form.setCreatedBy(user.getId().toString());
            return new ApiResponse<>(Boolean.TRUE, null, mapper.toDTO(this.service.save(mapper.toEntity(form))), null);
        }
        return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UserNotFound"),null,null);
    }

    @Override
    public ApiResponse<RoleDTO> update(Long id, RoleDTO form, Principal principal) {
        User user = this.repository.findByUserName(principal.getName()).get();
        if(user!=null) {
            Date date = new Date();
            form.setUpdatedAt(date);
            form.setUpdatedBy(user.getId().toString());
            if (this.service.find(id).get() != null) {
                return new ApiResponse<>(Boolean.TRUE, null, mapper.toDTO(this.service.update(id, mapper.toEntity(form))), null);
            }
            return new ApiResponse<>(Boolean.FALSE, null, null, null);
        }
        return new ApiResponse<>(Boolean.FALSE, Arrays.asList("UserNotFound"),null,null);
    }

    @Override
    public ApiResponse<Boolean> delete(Long id, Principal principal) {
        if(this.service.find(id).get()!=null){
            return new ApiResponse<>(Boolean.TRUE,null,this.service.delete(this.service.find(id).get()),null);
        }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }

    @GetMapping("/permissions")
    public ApiResponse<List<AuthorityDTO>> listPermission(){
        List<Authority> list = this.permissionService.findAllList();
        return  new ApiResponse<>(Boolean.TRUE,null,list.stream().map(this.authorityMapper::toDTO).collect(Collectors.toList()), null);
    }
}
