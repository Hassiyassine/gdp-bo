package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.patrimoine.TypeRelation;
import com.auth.detailes.web.dto.TypeRelationDTO;
import org.springframework.stereotype.Component;


@Component
public class TypeRelationMapper extends GenericMapper<TypeRelation, TypeRelationDTO> {


    @Override
    public TypeRelation toEntity(TypeRelationDTO dto) {
        return  TypeRelation.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .description(dto.getDescription())
                .build();
    }


    @Override
    public TypeRelationDTO toDTO(TypeRelation entity) {
        return TypeRelationDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .description(entity.getDescription())
                .build();
    }
}
