package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.referentiel.TypeRegim;
import com.auth.detailes.web.dto.TypeRegimDTO;
import org.springframework.stereotype.Component;


@Component
public class TypeRegimMapper extends GenericMapper<TypeRegim, TypeRegimDTO> {


    @Override
    public TypeRegim toEntity(TypeRegimDTO dto) {
        return  TypeRegim.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .description(dto.getDescription())
                .uid(dto.getUid())
                .build();
    }


    @Override
    public TypeRegimDTO toDTO(TypeRegim entity) {
        return TypeRegimDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .description(entity.getDescription())
                .uid(entity.getUid())
                .build();
    }
}
