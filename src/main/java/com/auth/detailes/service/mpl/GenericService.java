package com.auth.detailes.service.mpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenericService <T>{

    public Page<T> findAll(Object searchObject, Pageable pageable);
    public Optional<T> find(Long id);
    public boolean existsByCode(String code);
    public T save(T entity);
    public T update (Long id, T entity);
    public boolean delete(T entity);

}
