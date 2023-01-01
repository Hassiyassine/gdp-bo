package com.auth.detailes.web.common;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


public interface DefaultCrudOperations<T, D> {

    @GetMapping("/")
    public ApiResponse<Page<?>> findAllByCriteria(D searchRequest);

    @GetMapping("/{id}")
    public ApiResponse<T> findOneById(@PathVariable Long id);

    @GetMapping("/exists/{code}")
    public ApiResponse<Boolean> exists(@PathVariable String code);

    @PostMapping("/")
    public ApiResponse<T> add(@RequestBody  T form);

    @PutMapping("/{id}")
    public ApiResponse<T> update(@PathVariable Long id, @RequestBody T form) ;

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(@PathVariable Long id);
}
