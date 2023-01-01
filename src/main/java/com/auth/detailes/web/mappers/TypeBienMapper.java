package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.referentiel.TypeBien;
import com.auth.detailes.web.dto.TypeBienDTO;
import org.springframework.stereotype.Component;


@Component
public class TypeBienMapper extends GenericMapper<TypeBien, TypeBienDTO> {


    @Override
    public TypeBien toEntity(TypeBienDTO dto) {
        return  TypeBien.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .description(dto.getDescription())
                .uid(dto.getUid())
                .build();
    }


    @Override
    public TypeBienDTO toDTO(TypeBien entity) {
        return TypeBienDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .description(entity.getDescription())
                .uid(entity.getUid())
                .build();
    }
}
