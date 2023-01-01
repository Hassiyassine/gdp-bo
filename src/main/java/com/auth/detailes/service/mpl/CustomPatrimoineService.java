package com.auth.detailes.service.mpl;

import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.business.repositories.patrimoine.PatrimoineRepository;
import com.auth.detailes.utilities.enums.Status;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.requests.PatrimoineSearchRequest;
import com.auth.detailes.web.requests.ReferentialSearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class CustomPatrimoineService implements PatrimoineService {
    private final PatrimoineRepository repository;


    @Override
    public Page<Patrimoine> findAll(Object searchObject, Pageable pageable) {
        if (searchObject instanceof PatrimoineSearchRequest) {
            PatrimoineSearchRequest searchRequest = (PatrimoineSearchRequest) searchObject;
            Specification<Patrimoine> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("name"),"%"+ searchRequest.getName() +"%"));
                }
                if (searchRequest.getVille() != null && !searchRequest.getVille().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("ville"), "%"+searchRequest.getVille()+"%"));
                }
                if (searchRequest.getNumero() != null && !searchRequest.getNumero().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("numero"), searchRequest.getNumero()));
                }
                if (searchRequest.getTypeRegimCode() != null && !searchRequest.getTypeRegimCode().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("typeRegim").get("code"), searchRequest.getTypeRegimCode()));
                }

                if (searchRequest.getIsNotifiedParent() != null ) {
                    predicates.add(criteriaBuilder.equal(root.get("isNotified"), searchRequest.getIsNotifiedParent()));
                }
                if (searchRequest.getIsNotifiedContract() != null ) {
                    predicates.add(criteriaBuilder.equal(root.get("contract").get("isNotified"), searchRequest.getIsNotifiedContract()));
                }
                if (searchRequest.getTypeBienCode() != null && !searchRequest.getTypeBienCode().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("typeBien").get("code"), searchRequest.getTypeBienCode()));
                }
                if (searchRequest.getCreateBy() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("createdBy"), searchRequest.getCreateBy()));
                }
                if (searchRequest.getSort().equals("asc")) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(searchRequest.getSortColumn())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchRequest.getSortColumn())));
                }
                if (searchRequest.getDateCreationfrom() != null && !searchRequest.getDateCreationfrom().isEmpty()) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), DateTimeConverter.getInstantStartOfDayFromString(searchRequest.getDateCreationfrom(), DateTimeConverter.FORMAT_YYYY_MM_DD)));
                }
                if (searchRequest.getDateCreationTo() != null && !searchRequest.getDateCreationTo().isEmpty()) {
                    predicates.add(criteriaBuilder.lessThan(root.get("createdAt"), DateTimeConverter.getInstantEndOfDayFromString(searchRequest.getDateCreationTo(), DateTimeConverter.FORMAT_YYYY_MM_DD)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            return this.repository.findAll(specification, pageable);
        } else if (searchObject instanceof ReferentialSearchRequest) {
            ReferentialSearchRequest searchRequest = (ReferentialSearchRequest) searchObject;
            return this.repository.findAll(pageable);
        }
        return Page.empty();
    }

    @Override
    public Optional<Patrimoine> find(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return this.repository.existsByName(code);
    }

    @Override
    public Patrimoine save(Patrimoine entity) {
        return this.repository.save(entity);
    }

    @Override
    public Patrimoine update(Long id, Patrimoine entity) {
        Optional<Patrimoine> optional = this.find(id);
        if(optional.isPresent()){
            Patrimoine current = optional.get();

            if(entity.getGeoJson()!=null){
                current.setGeoJson(entity.getGeoJson());
            }
            if(entity.getAddress()!=null){
                current.setAddress(entity.getAddress());
            }
            if(entity.getName()!=null){
                current.setName(entity.getName());
            }
            if(entity.getProprietaire()!=null){
                current.setProprietaire(entity.getProprietaire());
            }
            if(entity.getTypeBien()!=null){
                current.setTypeBien(entity.getTypeBien());
            }
            if(entity.getTypeRegim()!=null){
                current.setTypeRegim(entity.getTypeRegim());
            }
            if(entity.getNumero()!=null){
                current.setNumero(entity.getNumero());
            }
            if(entity.getSuperficie()!=null){
                current.setSuperficie(entity.getSuperficie());
            }
            if(entity.getUtilisation()!=null){
                current.setUtilisation(entity.getUtilisation());
            }
            if(entity.getVille()!=null){
                current.setVille(entity.getVille());
            }
            if(entity.getAdresse()!=null){
                current.setAdresse(entity.getAdresse());
            }
            if(entity.getAffectation()!=null){
                current.setAffectation(entity.getAffectation());
            }
            if(entity.getDateHomologation()!=null){
                current.setDateHomologation(entity.getDateHomologation());
            }
            if(entity.getDateHomologationExpiration()!=null){
                current.setDateHomologationExpiration(entity.getDateHomologationExpiration());
            }
            if(entity.getDetailsApposition()!=null){
                current.setDetailsApposition(entity.getDetailsApposition());
            }
            if(entity.getDescription()!=null){
                current.setDescription(entity.getDescription());
            }
            if(entity.getStatus()!=null){
                current.setStatus(entity.getStatus());
            }
            if(entity.getPhotos()!=null){
                current.setPhotos(entity.getPhotos());
            }
            if(entity.getGeoJson()!=null){
                current.setGeoJson(entity.getGeoJson());
            }
            if(entity.getDocuments()!=null){
                current.setDocuments(entity.getDocuments());
            }
            if(entity.getEvents()!=null){
                current.setEvents(entity.getEvents());
            }
            if(entity.getTags()!=null){
                current.setTags(entity.getTags());
            }
            if(entity.getPatrimoineAttacheds()!=null){
                current.setPatrimoineAttacheds(entity.getPatrimoineAttacheds());
            }
            if(StringUtils.hasText(entity.getRatio())){
                current.setRatio(entity.getRatio());
            }
            if(StringUtils.hasText(entity.getPrivatetypeTexe())){
                current.setPrivatetypeTexe(entity.getPrivatetypeTexe());
            }
            if(StringUtils.hasText(entity.getSuperficieTax())){
                current.setSuperficieTax(entity.getSuperficieTax());
            }
            if(StringUtils.hasText(entity.getTypeTerrain())){
                current.setTypeTerrain(entity.getTypeTerrain());
            }
            if(StringUtils.hasText(entity.getTypeTexe())){
                current.setTypeTexe(entity.getTypeTexe());
            }

            current.setContract(entity.getContract());
            return  this.save(current);
        }
        return null;
    }

    @Override
    public boolean delete(Patrimoine entity) {
        return false;
    }

    @Override
    @Transactional
    public List<Patrimoine> findAllByCreatedBy(Long id) {
        return this.repository.findAllByCreatedBy(id);
    }

    @Override
    public List<Patrimoine> findAll(Boolean isNotified) {
        return this.repository.findAllByIsNotified(isNotified);
    }

    @Override
    public Long count() {
        return this.repository.count();
    }

    @Override
    public Long countByUserId(Long id) {
        return null;
    }

    @Override
    public Boolean archived(Patrimoine current) {
        boolean isArchived = false;
        try {
            current.setStatus(Status.ARCHIVED);
            this.save(current);
            isArchived = true;
        }catch (Exception e){
        log.error("cant to archived error : {}",e.getMessage());
        }
        return isArchived;
    }
}
