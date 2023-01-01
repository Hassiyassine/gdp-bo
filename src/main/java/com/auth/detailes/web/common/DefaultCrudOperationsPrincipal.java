package com.auth.detailes.web.common;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


public interface DefaultCrudOperationsPrincipal<T, D> {

    @GetMapping("/")
    public ApiResponse<Page<?>> findAllByCriteria(D searchRequest, Principal principal);

    @GetMapping("/{id}")
    public ApiResponse<T> findOneById(@PathVariable Long id, Principal principal);

    @GetMapping("/exists/{code}")
    public ApiResponse<Boolean> exists(@PathVariable String code, Principal principal);

    @PostMapping("/")
    public ApiResponse<T> add(@RequestBody  T form, Principal principal);

    @PutMapping("/{id}")
    public ApiResponse<T> update(@PathVariable Long id, @RequestBody T form, Principal principal) ;

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(@PathVariable Long id, Principal principal);
}
