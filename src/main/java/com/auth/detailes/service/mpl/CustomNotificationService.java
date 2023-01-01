package com.auth.detailes.service.mpl;

import com.auth.detailes.business.entites.patrimoine.Notification;
import com.auth.detailes.business.repositories.patrimoine.NotificationRepository;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.requests.NotificationSearchRequest;
import com.auth.detailes.web.requests.PatrimoineSearchRequest;
import com.auth.detailes.web.requests.ReferentialSearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CustomNotificationService implements NotificationService {
    private final NotificationRepository repository;


    @Override
    public Page<Notification> findAll(Object searchObject, Pageable pageable) {
        if (searchObject instanceof NotificationSearchRequest) {
            NotificationSearchRequest searchRequest = (NotificationSearchRequest) searchObject;
            Specification<Notification> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("patrimoine").get("name"),"%"+ searchRequest.getName() +"%"));
                }
                if (searchRequest.getVille() != null && !searchRequest.getVille().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("patrimoine").get("ville"), "%"+searchRequest.getVille()+"%"));
                }
                if (searchRequest.getNumero() != null && !searchRequest.getNumero().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("patrimoine").get("numero"), searchRequest.getNumero()));
                }
                if (searchRequest.getTypeRegimCode() != null && !searchRequest.getTypeRegimCode().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("patrimoine").get("typeRegim").get("code"), searchRequest.getTypeRegimCode()));
                }
                if (searchRequest.getTypeBienCode() != null && !searchRequest.getTypeBienCode().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("patrimoine").get("typeBien").get("code"), searchRequest.getTypeBienCode()));
                }
                if (searchRequest.getCreateBy() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("patrimoine").get("createdBy"), searchRequest.getCreateBy()));
                }
                if (searchRequest.getSort().equals("asc")) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(searchRequest.getSortColumn())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchRequest.getSortColumn())));
                }
                if (searchRequest.getDateCreationfrom() != null && !searchRequest.getDateCreationfrom().isEmpty()) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("patrimoine").get("createdAt"), DateTimeConverter.getInstantStartOfDayFromString(searchRequest.getDateCreationfrom(), DateTimeConverter.FORMAT_YYYY_MM_DD)));
                }
                if (searchRequest.getDateCreationTo() != null && !searchRequest.getDateCreationTo().isEmpty()) {
                    predicates.add(criteriaBuilder.lessThan(root.get("patrimoine").get("createdAt"), DateTimeConverter.getInstantEndOfDayFromString(searchRequest.getDateCreationTo(), DateTimeConverter.FORMAT_YYYY_MM_DD)));
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
    public Optional<Notification> find(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    @Override
    public Notification save(Notification entity) {
        return this.repository.save(entity);
    }

    @Override
    public Notification update(Long id, Notification entity) {
        Optional<Notification> optional = this.find(id);
        if(optional.isPresent()){
            Notification current = optional.get();
            return  this.save(current);
        }
        return null;
    }

    @Override
    public boolean delete(Notification entity) {
        return false;
    }

    @Override
    public List<Notification> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Notification> findByPatrimoineId(Long id) {
        return this.repository.findByPatrimoineId(id);
    }

}
