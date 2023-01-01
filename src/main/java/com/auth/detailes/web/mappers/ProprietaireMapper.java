package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.patrimoine.Proprietaire;
import com.auth.detailes.web.dto.ProprietaireDTO;
import org.springframework.stereotype.Component;


@Component
public class ProprietaireMapper extends GenericMapper<Proprietaire, ProprietaireDTO> {


    @Override
    public Proprietaire toEntity(ProprietaireDTO dto) {
        return  Proprietaire.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .description(dto.getDescription())
                .build();
    }


    @Override
    public ProprietaireDTO toDTO(Proprietaire entity) {
        return ProprietaireDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .description(entity.getDescription())
                .build();
    }
}
