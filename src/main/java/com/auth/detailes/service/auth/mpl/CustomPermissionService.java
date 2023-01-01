package com.auth.detailes.service.auth.mpl;

import com.auth.detailes.business.entites.auth.Authority;
import com.auth.detailes.business.repositories.auth.AuthorityRepository;
import com.auth.detailes.service.auth.PermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CustomPermissionService implements PermissionService {


    private final AuthorityRepository repository;


    @Override
    public Page<Authority> findAll(Object searchObject, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Authority> find(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    @Override
    public Authority save(Authority entity) {
        return null;
    }

    @Override
    public Authority update(Long id, Authority entity) {
        return null;
    }

    @Override
    public boolean delete(Authority entity) {
        return false;
    }

    @Override
    public List<Authority> findAllList() {
        return this.repository.findAll();
    }


}
