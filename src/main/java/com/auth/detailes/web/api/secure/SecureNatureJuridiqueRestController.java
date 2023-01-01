package com.auth.detailes.web.api.secure;

import com.auth.detailes.business.entites.referentiel.NatureJuridique;
import com.auth.detailes.service.referentiel.NatureJuridiqueService;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.DefaultCrudOperations;
import com.auth.detailes.web.common.EndPointConstent;
import com.auth.detailes.web.dto.ReferentialDTO;
import com.auth.detailes.web.mappers.NatureJuridiqueMapper;
import com.auth.detailes.web.requests.ReferentialSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(EndPointConstent.NATURE_JURIDIQUE_ENDPOINT_REST)
public class SecureNatureJuridiqueRestController extends CustomRestController implements DefaultCrudOperations<ReferentialDTO, ReferentialSearchRequest> {

    private final NatureJuridiqueService service;
    private final NatureJuridiqueMapper mapper;


    @Override
    public ApiResponse<Page<?>> findAllByCriteria(ReferentialSearchRequest searchRequest) {
        Page<NatureJuridique> page = this.service.findAll(searchRequest, PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
        return new ApiResponse<>(Boolean.TRUE,null,page.map(mapper::toDTO),null);
    }

    @Override
    public ApiResponse<ReferentialDTO> findOneById(Long id) {
        return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(this.service.find(id).get()),null);
    }

    @Override
    public ApiResponse<Boolean> exists(String code) {
        return null;
    }

    @Override
    public ApiResponse<ReferentialDTO> add(ReferentialDTO form) {
        return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(this.service.save(mapper.toEntity(form))),null);
    }

    @Override
    public ApiResponse<ReferentialDTO> update(Long id, ReferentialDTO form) {
        if(this.service.find(id).get()!=null){
            return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(this.service.update(id,mapper.toEntity(form))),null);
        }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        if(this.service.find(id).get()!=null){
            return new ApiResponse<>(Boolean.TRUE,null,this.service.delete(this.service.find(id).get()),null);
        }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }



}
