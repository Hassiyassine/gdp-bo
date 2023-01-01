package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.auth.Authority;
import com.auth.detailes.web.dto.AuthorityDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper extends  GenericMapper<Authority, AuthorityDTO>{
    @Override
    public Authority toEntity(AuthorityDTO dto) {
        return Authority.builder()
                .id(dto.getId())
                .codeAuthority(dto.getCodeAuthority())
                .label(dto.getLibelle())
                .description(dto.getRoleDescription())
                .build();
    }

    @Override
    public AuthorityDTO toDTO(Authority entity) {
        return AuthorityDTO.builder()
                .id(entity.getId())
                .codeAuthority(entity.getCodeAuthority())
                .libelle(entity.getLabel())
                .roleDescription(entity.getDescription())
                .build();
    }
}
