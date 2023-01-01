package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.dto.PatrimoineDTO;
import com.auth.detailes.web.responses.PatrimoineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class PatrimoineMapper extends GenericMapper<Patrimoine, PatrimoineDTO> {

    @Autowired
    private ProprietaireMapper proprietaireMapper;
    @Autowired
    private TypeRegimMapper typeRegimMapper;
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private TypeBienMapper typeBienMapper;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private PatrimoineMapper patrimoineMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private PatrimoineAttachedMapper patrimoineAttachedMapper;



    @Override
    public Patrimoine toEntity(PatrimoineDTO dto) {
        return  Patrimoine.builder()
                .id(dto.getId())
                .name(dto.getName())
                .proprietaire(dto.getProprietaire()!=null?this.proprietaireMapper.toEntity(dto.getProprietaire()):null)
                .typeBien(dto.getTypeBien()!=null?this.typeBienMapper.toEntity(dto.getTypeBien()):null)
                .typeRegim(dto.getTypeRegim()!=null?this.typeRegimMapper.toEntity(dto.getTypeRegim()):null)
                .numero(dto.getNumero())
                .superficie(dto.getSuperficie())
                .utilisation(dto.getUtilisation())
                .ville(dto.getVille())
                .address(dto.getAddress())
                .affectation(dto.getAffectation())
                .dateHomologation(DateTimeConverter.toDate(dto.getDateHomologation()))
                .dateHomologationExpiration(DateTimeConverter.toDate(dto.getDateHomologationExpiration()))
                .detailsApposition(DateTimeConverter.toDate(dto.getDetailsApposition()))
                .patrimoineAttacheds(dto.getPatrimoineAttacheds()!=null? dto.getPatrimoineAttacheds().stream().map(this.patrimoineAttachedMapper::toEntity).collect(Collectors.toSet()):null)
                .photos(dto.getPhotos()!=null && !dto.getPhotos().isEmpty()?dto.getPhotos().stream().map(this.documentMapper::toEntity).collect(Collectors.toSet()):null)
                .documents(dto.getDocuments()!=null && !dto.getDocuments().isEmpty()?dto.getDocuments().stream().map(this.documentMapper::toEntity).collect(Collectors.toSet()):null)
                .isDeleted(dto.getIsDeleted())
                .contract(dto.getContract()!=null?this.contractMapper.toEntity(dto.getContract()):null)
                .tags(dto.getTags()!=null && !dto.getTags().isEmpty()?dto.getTags().stream().map(this.tagMapper::toEntity).collect(Collectors.toSet()) : null)
                .description(dto.getDescription())
                .ratio(dto.getRatio())
                .privatetypeTexe(dto.getPrivatetypeTexe())
                .superficieTax(dto.getSuperficieTax())
                .typeTerrain(dto.getTypeTerrain())
                .typeTexe(dto.getTypeTexe())
                .build();
    }


    @Override
    public PatrimoineDTO toDTO(Patrimoine entity) {
        return  PatrimoineDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .proprietaire(entity.getProprietaire()!=null?this.proprietaireMapper.toDTO(entity.getProprietaire()):null)
                .typeBien(entity.getTypeBien()!=null?this.typeBienMapper.toDTO(entity.getTypeBien()):null)
                .typeRegim(entity.getTypeRegim()!=null?this.typeRegimMapper.toDTO(entity.getTypeRegim()):null)
                .numero(entity.getNumero())
                .superficie(entity.getSuperficie())
                .utilisation(entity.getUtilisation())
                .ville(entity.getVille())
                .address(entity.getAddress())
                .affectation(entity.getAffectation())
                .dateHomologation(DateTimeConverter.toString(entity.getDateHomologation()))
                .dateHomologationExpiration(DateTimeConverter.toString(entity.getDateHomologationExpiration()))
                .detailsApposition(DateTimeConverter.toString(entity.getDetailsApposition()))
                .patrimoineAttacheds(entity.getPatrimoineAttacheds()!=null? entity.getPatrimoineAttacheds().stream().map(this.patrimoineAttachedMapper::toDTO).collect(Collectors.toList()):null)
                .photos(entity.getPhotos()!=null && !entity.getPhotos().isEmpty()?entity.getPhotos().stream().map(this.documentMapper::toDTO).collect(Collectors.toList()):null)
                .documents(entity.getDocuments()!=null && !entity.getDocuments().isEmpty()?entity.getDocuments().stream().map(this.documentMapper::toDTO).collect(Collectors.toList()):null)
                .isDeleted(entity.getIsDeleted())
                .contract(entity.getContract()!=null?this.contractMapper.toDTO(entity.getContract()):null)
                .tags(entity.getTags()!=null && !entity.getTags().isEmpty()?entity.getTags().stream().map(this.tagMapper::toDTO).collect(Collectors.toList()) : null)
                .description(entity.getDescription())
                .status(entity.getStatus())
                .ratio(entity.getRatio())
                .privatetypeTexe(entity.getPrivatetypeTexe())
                .superficieTax(entity.getSuperficieTax())
                .typeTerrain(entity.getTypeTerrain())
                .typeTexe(entity.getTypeTexe())
                //.geoJson(entity.getGeoJson()!=null?this)
                .build();
    }

    public PatrimoineResponse toResponse(Patrimoine entity) {
        return  PatrimoineResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .proprietaire(entity.getProprietaire()!=null?this.proprietaireMapper.toDTO(entity.getProprietaire()):null)
                .typeBien(entity.getTypeBien()!=null?this.typeBienMapper.toDTO(entity.getTypeBien()):null)
                .typeRegim(entity.getTypeRegim()!=null?this.typeRegimMapper.toDTO(entity.getTypeRegim()):null)
                .numero(entity.getNumero())
                .superficie(entity.getSuperficie())
                .utilisation(entity.getUtilisation())
                .ville(entity.getVille())
                .address(entity.getAddress())
                .affectation(entity.getAffectation())
                .dateHomologation(DateTimeConverter.toString(entity.getDateHomologation()))
                .dateHomologationExpiration(DateTimeConverter.toString(entity.getDateHomologationExpiration()))
                .detailsApposition(DateTimeConverter.toString(entity.getDetailsApposition()))
                .imageCode(entity.getPhotos() != null && entity.getPhotos().size() >0 && entity.getPhotos().stream().anyMatch(Document::getIsPrimary) ? entity.getPhotos().stream().filter(Document::getIsPrimary).findFirst().orElse(null).getUniqueCode() :null)
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }

    public Patrimoine toEntity(PatrimoineResponse dto) {
        return  Patrimoine.builder()
                .id(dto.getId())
                .name(dto.getName())
                .numero(dto.getNumero())
                .superficie(dto.getSuperficie())
                .utilisation(dto.getUtilisation())
                .ville(dto.getVille())
                .address(dto.getAddress())
                .affectation(dto.getAffectation())
                .dateHomologation(DateTimeConverter.toDate(dto.getDateHomologation()))
                .dateHomologationExpiration(DateTimeConverter.toDate(dto.getDateHomologationExpiration()))
                .detailsApposition(DateTimeConverter.toDate(dto.getDetailsApposition()))
                .build();
    }
}
