package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.referentiel.NatureJuridique;
import com.auth.detailes.web.dto.ReferentialDTO;
import org.springframework.stereotype.Component;

@Component
public class NatureJuridiqueMapper extends GenericMapper<NatureJuridique, ReferentialDTO>{

    @Override
    public NatureJuridique toEntity(ReferentialDTO dto) {
        return NatureJuridique.builder()
                .id(dto.getId())
                .label(dto.getLibelle())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public ReferentialDTO toDTO(NatureJuridique entity) {
        if (entity==null)
        {
            return null;
        }
        return ReferentialDTO.builder()
                .id(entity.getId())
                .libelle(entity.getLabel())
                .description(entity.getDescription())
                .build();
    }
}
