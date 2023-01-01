package com.auth.detailes.service.mpl.referentiel;

import com.auth.detailes.business.entites.referentiel.NatureJuridique;
import com.auth.detailes.business.repositories.referentiel.NatureJuridiqueRepository;
import com.auth.detailes.service.referentiel.NatureJuridiqueService;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.requests.ReferentialSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomNatureJuridiqueService implements NatureJuridiqueService {


    private final NatureJuridiqueRepository repository;

    @Override
    public Page<NatureJuridique> findAll(Object searchObject, Pageable pageable) {
        if (searchObject instanceof ReferentialSearchRequest) {
            ReferentialSearchRequest searchRequest = (ReferentialSearchRequest) searchObject;
            Specification<NatureJuridique> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (searchRequest.getLibelle() != null && !searchRequest.getLibelle().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("libelle"),"%"+ searchRequest.getLibelle() +"%"));
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
    public Optional<NatureJuridique> find(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    @Override
    public NatureJuridique save(NatureJuridique entity) {
        Date now = new Date();
        entity.setCreatedAt(DateTimeConverter.toString(now));
        return this.repository.save(entity);
    }

    @Override
    public NatureJuridique update(Long id, NatureJuridique entity) {
        Optional<NatureJuridique> current = this.repository.findById(id);
        if(current.isPresent()){
            current.get().setLabel(entity.getLabel());
            current.get().setDescription(entity.getDescription());
            Date now = new Date();
            current.get().setUpdatedAt(DateTimeConverter.toString(now));
            return this.repository.save(current.get());
        }
        return null;
    }

    @Override
    public boolean delete(NatureJuridique entity) {
        try {
            this.repository.delete(entity);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}