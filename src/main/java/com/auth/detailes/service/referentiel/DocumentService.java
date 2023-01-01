package com.auth.detailes.service.referentiel;

import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.service.mpl.GenericService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocumentService extends GenericService<Document> {
    Optional<Document> findByUniqeCode(String uniqueCode);
    List<Document> saveAllWidthUpload(List<Document> documents,List<MultipartFile> files,String directory);

}
