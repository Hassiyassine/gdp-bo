package com.auth.detailes.web.mappers;


import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.business.repositories.referentiel.TypeDocumentRepository;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper extends GenericMapper<Document, DocumentDTO>{

    @Autowired
    private TypeDocumentRepository typeDocument;
    @Autowired
    private TypeDocumentMapper typeDocumentMapper;
    @Override
    public Document toEntity(DocumentDTO dto) {
        return Document.builder()
                .description(dto.getDescription())
                .id(dto.getId())
                .filename(dto.getFilename())
                .path(dto.getPath())
                .label(dto.getLibelle())
                .isPrimary(dto.getIsPrimary())
                .uniqueCode(dto.getUniqueCode())
                .date_caducit(DateTimeConverter.toDate(dto.getDate_caducit()))
                .dateDel(DateTimeConverter.toDate(dto.getDateDel()))
                .typeDocument(dto.getTypeDocument()!=null ? typeDocumentMapper.toEntity(dto.getTypeDocument()) : null)
                .build();
    }

    @Override
    public DocumentDTO toDTO(Document entity) {
        return DocumentDTO.builder()
                .description(entity.getDescription())
                .id(entity.getId())
                .filename(entity.getFilename())
                //.path(entity.getPath())
                .libelle(entity.getLabel())
                .isPrimary(entity.getIsPrimary())
                .uniqueCode(entity.getUniqueCode())
                .date_caducit(DateTimeConverter.toString(entity.getDate_caducit()))
                .dateDel(DateTimeConverter.toString(entity.getDateDel()))
                .typeDocument(entity.getTypeDocument()!=null ? typeDocumentMapper.toDTO(entity.getTypeDocument()) : null)
                .build();
    }
}
