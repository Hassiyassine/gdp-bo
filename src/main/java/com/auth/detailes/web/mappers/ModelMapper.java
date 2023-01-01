package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.referentiel.Model;
import com.auth.detailes.utilities.enums.ModelQuestionEnum;
import com.auth.detailes.web.dto.ReferentialDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ModelMapper extends GenericMapper<Model, ReferentialDTO>{


    @Override
    public Model toEntity(ReferentialDTO dto) {
        return Model.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .label(dto.getLibelle())
                .build();
    }

    @Override
    public ReferentialDTO toDTO(Model entity) {
        return ReferentialDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .libelle(entity.getLabel())
                .code(entity.getCode())
                .build();
    }

   private String Convert(ModelQuestionEnum code){
        String codes  = code.toString();
       return codes;
    }
}
