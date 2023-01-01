package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.patrimoine.Contract;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.dto.ContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ContractMapper extends GenericMapper<Contract, ContractDTO> {

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public Contract toEntity(ContractDTO dto) {
        return  dto!=null ? Contract.builder()
                .id(dto.getId())
                .dateEnd(DateTimeConverter.toDate(dto.getDateEnd()))
                .dateStart(DateTimeConverter.toDate(dto.getDateStart()))
                .tauxaugmentation(dto.getTauxaugmentation())
                .proprietaire(dto.getProprietaire())
                .delaisAugmentation(dto.getDelaisAugmentation())
                .locataire(dto.getLocataire())
                .loyer(dto.getLoyer())
                .dateButtoire(DateTimeConverter.toDate(dto.getDateButtoire()))
                .document(dto.getDocument()!=null? documentMapper.toEntity(dto.getDocument()):null)
                .build()
                : null;
    }


    @Override
    public ContractDTO toDTO(Contract entity) {
        return entity != null ? ContractDTO.builder()
                .id(entity.getId())
                .dateEnd(DateTimeConverter.toString(entity.getDateEnd()))
                .dateStart(DateTimeConverter.toString(entity.getDateStart()))
                .tauxaugmentation(entity.getTauxaugmentation())
                .proprietaire(entity.getProprietaire())
                .delaisAugmentation(entity.getDelaisAugmentation())
                .locataire(entity.getLocataire())
                .loyer(entity.getLoyer())
                .dateButtoire(DateTimeConverter.toString(entity.getDateButtoire()))
                .document(entity.getDocument()!=null? documentMapper.toDTO(entity.getDocument()):null)
                .build()
                : null;
    }
}
