package com.auth.detailes.service.mpl.referentiel;

import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.business.repositories.common.DocumentRepository;
import com.auth.detailes.service.common.FileStorageService;
import com.auth.detailes.service.referentiel.DocumentService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomDocumentService implements DocumentService {

    private final DocumentRepository repository;
    private final FileStorageService service;

    @Override
    public Page<Document> findAll(Object searchObject, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Document> find(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    @Override
    public Document save(Document entity) {
        return this.repository.save(entity);
    }

    @Override
    public Document update(Long id, Document entity) {
        Optional<Document> optional = this.find(id);
        if(optional.isPresent()){
            Document current = optional.get();

            if(StringUtils.hasText(entity.getPath())){
                current.setPath(entity.getPath());
            }
            if(entity.getDate_caducit()!=null){
                current.setDate_caducit(entity.getDate_caducit());
            }
            if(entity.getDateDel()!=null){
                current.setDateDel(entity.getDateDel());
            }
            if(entity.getIsNotified()!=null){
                current.setIsNotified(entity.getIsNotified());
            }
            return repository.save(current);
        }
        return null;
    }

    @Override
    public boolean delete(Document entity) {

        try{
            this.repository.delete(entity);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Document> findByUniqeCode(String uniqueCode) {
        return this.repository.findTopByUniqueCodeOrderByIdDesc(uniqueCode);
    }


    @Override
    @Transactional
    public List<Document> saveAllWidthUpload(List<Document> documents, List<MultipartFile> files, String directory) {

        return this.saveOrUpdate(documents
                .stream()
                .map(e-> {
                            if(files.stream().anyMatch(file-> Objects.equals(FilenameUtils.removeExtension(file.getOriginalFilename()), e.getUniqueCode())) ){
                                String filename = String.format("sigma_%s.%s",e.getUniqueCode(),this.service.getExtension(this.getFileFom(files,e).getOriginalFilename()).get());
                                e.setPath(this.service.uplodeCustomName(this.getFileFom(files,e),directory,filename));
                                e.setFilename(filename);
                                e.setLabel("IMAGE");
                                e.setIsPrimary(e.getIsPrimary()!=null?e.getIsPrimary():Boolean.FALSE);
                                e.setIsNotified(Boolean.FALSE);
                            }
                            return e;
                        }).collect(Collectors.toList()));

    }

    private MultipartFile getFileFom(List<MultipartFile> files,Document document){
        files.stream().forEach(e-> System.err.println(FilenameUtils.removeExtension(e.getOriginalFilename())+" "+ document.getUniqueCode()));
        return files.stream().filter(e-> Objects.equals(FilenameUtils.removeExtension(e.getOriginalFilename()), document.getUniqueCode())).findFirst().get();
    }

    @Transactional
    private List<Document> saveOrUpdate(List<Document> entities) {
        return entities.stream().map(e->{
            if(e.getId()==null){
               return this.save(e);
            }else {
                return this.update(e.getId(),e);
            }
        }).collect(Collectors.toList());
    }
}