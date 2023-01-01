package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.referentiel.TypeDocument;
import com.auth.detailes.web.dto.TypeDocumentDTO;
import org.springframework.stereotype.Component;

@Component
public class TypeDocumentMapper extends GenericMapper<TypeDocument, TypeDocumentDTO> {


    @Override
    public TypeDocument toEntity(TypeDocumentDTO dto) {
        return  TypeDocument.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .description(dto.getDescription())
                .uid(dto.getUid())
                .build();
    }

    @Override
    public TypeDocumentDTO toDTO(TypeDocument entity) {
        return TypeDocumentDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .description(entity.getDescription())
                .uid(entity.getUid())
                .code(entity.getCode())
                .build();
    }
}
