package com.auth.detailes.service.mpl.referentiel;

import com.auth.detailes.business.entites.referentiel.TypeRegim;
import com.auth.detailes.business.repositories.referentiel.TypeRegimRepository;
import com.auth.detailes.service.referentiel.TypeRegimService;
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
public class CustomTypeRegimService implements TypeRegimService {
    private final TypeRegimRepository repository;


    @Override
    public Page<TypeRegim> findAll(Object searchObject, Pageable pageable) {
        if (searchObject instanceof ReferentialSearchRequest) {
            ReferentialSearchRequest searchRequest = (ReferentialSearchRequest) searchObject;
            Specification<TypeRegim> specification = (root, criteriaQuery, criteriaBuilder) -> {
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
    public Optional<TypeRegim> find(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    @Override
    public TypeRegim save(TypeRegim entity) {
        return null;
    }

    @Override
    public TypeRegim update(Long id, TypeRegim entity) {
        return null;
    }

    @Override
    public boolean delete(TypeRegim entity) {
        return false;
    }
}
