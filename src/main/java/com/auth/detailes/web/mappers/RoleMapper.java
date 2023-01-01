package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.auth.Authority;
import com.auth.detailes.business.entites.auth.Role;
import com.auth.detailes.web.dto.RoleDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoleMapper extends GenericMapper<Role, RoleDTO>{
    private final AuthorityMapper mapper;

    @Override
    public Role toEntity(RoleDTO dto) {
            return Role.builder()
                    .id(dto.getId())
                    .label(dto.getLibelle())
                    .description(dto.getDescription())
                    .code(dto.getCode())
                    .authorities(dto.getAuthoritys()!=null && dto.getAuthoritys().size()>0 ? dto.getAuthoritys().stream().map(this.mapper::toEntity).collect(Collectors.toSet()) : null)
                    .build();
    }

    @Override
    public RoleDTO toDTO(Role entity) {
        return RoleDTO.builder()
                .id(entity.getId())
                .libelle(entity.getLabel())
                .description(entity.getDescription())
                .code(entity.getCode())
                .authoritys(entity.getAuthorities()!=null && entity.getAuthorities().size()>0 ?entity.getAuthorities().stream().map(mapper::toDTO).collect(Collectors.toList()):null)
                .permissions(entity.getAuthorities().stream().map(
                        Authority::getCodeAuthority).collect(Collectors.toList()))
                .build();
    }
}
