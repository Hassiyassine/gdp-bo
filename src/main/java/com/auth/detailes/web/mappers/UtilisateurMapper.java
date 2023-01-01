package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.web.dto.UtilisateurDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UtilisateurMapper extends  GenericMapper<User, UtilisateurDTO>{

    private  final RoleMapper roleMapper;

    @Override
    public User toEntity(UtilisateurDTO dto) {
            return User.builder()
                    .id(dto.getId())
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .userName(dto.getUserName())
                    .enable(dto.isEnable())
                    .email(dto.getEmail())
                    .avatar(dto.getAvatar())
                    .role(this.roleMapper.toEntity(dto.getRole()))
                    .addressLine1(dto.getAddressLine1())
                    .addressLine2(dto.getAddressLine2())
                    .postalCode(dto.getPostalCode())
                    .personalPhone(dto.getPersonalPhone())
                    .professtionalPhone(dto.getProfesstionalPhone())
                    .password(dto.getPassword())
                    .fonction(dto.getFonction())
                    .description(dto.getDescription())
                    .build();

    }

    @Override
    public UtilisateurDTO toDTO(User entity) {

            return UtilisateurDTO.builder()
                    .id(entity.getId())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .userName(entity.getUsername())
                    .enable(entity.isEnable())
                    .email(entity.getEmail())
                    .avatar(entity.getAvatar())
                    .role(entity.getRole()!=null ?this.roleMapper.toDTO(entity.getRole()):null)
                    .addressLine1(entity.getAddressLine1())
                    .addressLine2(entity.getAddressLine2())
                    .postalCode(entity.getPostalCode())
                    .personalPhone(entity.getPersonalPhone())
                    .professtionalPhone(entity.getProfesstionalPhone())
                    .fonction(entity.getFonction())
                    .description(entity.getDescription())
                    .build();
    }
}
