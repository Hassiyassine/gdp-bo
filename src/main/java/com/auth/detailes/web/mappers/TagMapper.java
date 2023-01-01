package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.patrimoine.Tag;
import com.auth.detailes.web.dto.TagDTO;
import org.springframework.stereotype.Component;


@Component
public class TagMapper extends GenericMapper<Tag, TagDTO> {


    @Override
    public Tag toEntity(TagDTO dto) {
        return  Tag.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .description(dto.getDescription())

                .build();
    }


    @Override
    public TagDTO toDTO(Tag entity) {
        return TagDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .description(entity.getDescription())

                .build();
    }
}
