package com.auth.detailes.service.auth.mpl;


import com.auth.detailes.business.entites.auth.Role;
import com.auth.detailes.business.repositories.auth.RoleRepository;
import com.auth.detailes.service.auth.RoleService;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.requests.RoleSearchRequest;
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
@Slf4j
@AllArgsConstructor
public class CustomRoleService implements RoleService {


    private final RoleRepository repository;

    @Override
    public Page<Role> findAll(Object searchObject, Pageable pageable) {
        if (searchObject instanceof RoleSearchRequest) {
            RoleSearchRequest searchRequest = (RoleSearchRequest) searchObject;
            Specification<Role> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (searchRequest.getLibelle() != null && !searchRequest.getLibelle().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("libelle"),"%"+ searchRequest.getLibelle() +"%"));
                }
                if (searchRequest.getCode() != null && !searchRequest.getCode().isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("code"),"%"+ searchRequest.getCode() +"%"));
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
        }
        return Page.empty();
    }

    @Override
    public Optional<Role> find(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return this.repository.existsByCode(code);
    }

    @Override
    public Role save(Role entity) {
        return this.repository.save(entity);
    }

    @Override
    public Role update(Long id, Role entity) {
        Optional<Role> current = this.repository.findById(id);
        if(current.isPresent())
        {   current.get().setUpdatedAt(entity.getUpdatedAt());
            current.get().setUpdatedBy(entity.getUpdatedBy());
            current.get().setAuthorities(entity.getAuthorities());
            current.get().setDescription(entity.getDescription());
            current.get().setCode(entity.getCode());
            current.get().setLabel(entity.getLabel());
           return this.repository.save(current.get());
        }
        return null;
    }

    @Override
    public boolean delete(Role entity) {

        try {
        this.repository.delete(entity);
        }catch (Exception e){
            return  false;
        }
        return true;
    }
}
