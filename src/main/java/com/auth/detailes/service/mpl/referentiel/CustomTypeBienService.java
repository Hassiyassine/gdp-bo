package com.auth.detailes.service.mpl.referentiel;

import com.auth.detailes.business.entites.referentiel.TypeBien;
import com.auth.detailes.business.repositories.referentiel.TypeBienRepository;
import com.auth.detailes.service.referentiel.TypeBienService;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.requests.ReferentialSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CustomTypeBienService implements TypeBienService {
    private final TypeBienRepository repository;


    @Override
    public Page<TypeBien> findAll(Object searchObject, Pageable pageable) {
        if (searchObject instanceof ReferentialSearchRequest) {
            ReferentialSearchRequest searchRequest = (ReferentialSearchRequest) searchObject;
            Specification<TypeBien> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (searchRequest.getLibelle() != null && !searchRequest.getLibelle().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("label"),"%"+ searchRequest.getLibelle() +"%"));
                }
                if (searchRequest.getCode() != null && !searchRequest.getCode().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("code"), searchRequest.getCode()));
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
    public Optional<TypeBien> find(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    @Override
    public TypeBien save(TypeBien entity) {
        return null;
    }

    @Override
    public TypeBien update(Long id, TypeBien entity) {
        return null;
    }

    @Override
    public boolean delete(TypeBien entity) {
        return false;
    }
}
