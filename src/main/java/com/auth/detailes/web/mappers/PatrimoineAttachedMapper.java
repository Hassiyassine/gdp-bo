package com.auth.detailes.web.mappers;


import com.auth.detailes.business.entites.patrimoine.PatrimoineAttached;
import com.auth.detailes.web.dto.PatrimoineAttachedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PatrimoineAttachedMapper extends GenericMapper<PatrimoineAttached, PatrimoineAttachedDTO> {

    @Autowired
    private PatrimoineMapper mapper;
    @Autowired
    private TypeRelationMapper typeRelationMapper;


    @Override
    public PatrimoineAttached toEntity(PatrimoineAttachedDTO dto) {
        return  PatrimoineAttached.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .typeRelation(dto.getTypeRelation()!=null?this.typeRelationMapper.toEntity(dto.getTypeRelation()):null)
                .patrimoineAttached(dto.getPatrimoineAttached()!=null?this.mapper.toEntity(dto.getPatrimoineAttached()):null)
                .build();
    }

    @Override
    public PatrimoineAttachedDTO toDTO(PatrimoineAttached entity) {
        return  PatrimoineAttachedDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .typeRelation(entity.getTypeRelation()!=null?this.typeRelationMapper.toDTO(entity.getTypeRelation()):null)
                .patrimoineAttached(entity.getPatrimoineAttached()!=null?this.mapper.toResponse(entity.getPatrimoineAttached()):null)
                .build();
    }
}
