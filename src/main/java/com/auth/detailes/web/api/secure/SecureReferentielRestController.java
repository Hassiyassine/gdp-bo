package com.auth.detailes.web.api.secure;


import com.auth.detailes.business.entites.patrimoine.Proprietaire;
import com.auth.detailes.business.entites.patrimoine.Tag;
import com.auth.detailes.business.entites.patrimoine.TypeRelation;
import com.auth.detailes.business.entites.referentiel.TypeBien;
import com.auth.detailes.business.entites.referentiel.TypeRegim;
import com.auth.detailes.service.referentiel.*;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.EndPointConstent;
import com.auth.detailes.web.dto.*;
import com.auth.detailes.web.mappers.*;
import com.auth.detailes.web.requests.ReferentialSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(EndPointConstent.REFERENTIEL_ENDPOINT_REST)
@AllArgsConstructor
public class SecureReferentielRestController extends CustomRestController {

    private final TypeBienService typeBienService;
    private final TypeBienMapper typeBienMapper;

    private final TypeRegimService typeRegimService;
    private final TypeRegimMapper typeRegimMapper;

    private final ProprietaireMapper proprietaireMapper;
    private final ProprietaireService proprietaireService;

    private final TagService tagService;
    private final TagMapper tagMapper;
    private final TypeRelationService typeRelationService;
    private final TypeRelationMapper typeRelationMapper;


    @GetMapping("/type-bien")
    public ApiResponse<Page<TypeBienDTO>> findAllTypeBien(ReferentialSearchRequest searchRequest) {
        Page<TypeBien> page = typeBienService.findAll(searchRequest,PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
        return new ApiResponse<>(Boolean.TRUE,null,page.map(this.typeBienMapper::toDTO),null);
    }

    @GetMapping("/type-regim")
    public ApiResponse<Page<TypeRegimDTO>> findAllTypeRegim(ReferentialSearchRequest searchRequest) {
        Page<TypeRegim> page = typeRegimService.findAll(searchRequest,PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
        return new ApiResponse<>(Boolean.TRUE,null,page.map(this.typeRegimMapper::toDTO),null);
    }

    @GetMapping("/tags")
    public ApiResponse<Page<TagDTO>> findAllTags(ReferentialSearchRequest searchRequest) {
        Page<Tag> page = tagService.findAll(searchRequest,PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
        return new ApiResponse<>(Boolean.TRUE,null,page.map(this.tagMapper::toDTO),null);
    }

    @GetMapping("/proprietaires")
    public ApiResponse<Page<ProprietaireDTO>> findAllProprietaireس(ReferentialSearchRequest searchRequest) {
        Page<Proprietaire> page = proprietaireService.findAll(searchRequest,PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
        return new ApiResponse<>(Boolean.TRUE,null,page.map(this.proprietaireMapper::toDTO),null);
    }

    @GetMapping("/type-relation")
    public ApiResponse<Page<TypeRelationDTO>> findAllTypeRelationس(ReferentialSearchRequest searchRequest) {
        Page<TypeRelation> page = typeRelationService.findAll(searchRequest,PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
        return new ApiResponse<>(Boolean.TRUE,null,page.map(this.typeRelationMapper::toDTO),null);
    }
}
