package com.auth.detailes.web.api.secure;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.service.mpl.PatrimoineService;
import com.auth.detailes.service.referentiel.DocumentService;
import com.auth.detailes.utilities.StringGeneratorCode;
import com.auth.detailes.utilities.enums.Status;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.EndPointConstent;
import com.auth.detailes.web.dto.PatrimoineDTO;
import com.auth.detailes.web.dto.geojson.FeaturesDTO;
import com.auth.detailes.web.mappers.DocumentMapper;
import com.auth.detailes.web.mappers.PatrimoineMapper;
import com.auth.detailes.web.requests.PatrimoineSearchRequest;
import com.auth.detailes.web.validators.PatrimoineValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;



@RestController
@AllArgsConstructor
@RequestMapping(EndPointConstent.PATRIMOINE_ENDPOINT_REST)
@Slf4j
public class SecurePatrimoineRestController extends CustomRestController {

    private final PatrimoineService service;
    private final PatrimoineMapper mapper;
    private final PatrimoineValidator validator;
    private final DocumentService documentService;
    private final DocumentMapper documentMapper;
    private final Gson gson;
    private ObjectMapper objectMapper;


    @GetMapping("/")
    public ApiResponse<Page<?>> findAllByCriteria(PatrimoineSearchRequest searchRequest, Principal principal) {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            searchRequest.setCreateBy(optional.get().getId());
            Page<Patrimoine> page = this.service.findAll(searchRequest, PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
            return new ApiResponse<>(Boolean.TRUE,null,page.map(mapper::toResponse),null);
        }
        return new ApiResponse<>(Boolean.FALSE,Collections.singletonList(""),null,null);
    }

    @GetMapping("/{id}")
    public ApiResponse<PatrimoineDTO> findOneById(@PathVariable Long id, Principal principal) {
       Optional<User> opt = this.userService.findAccountByLogin(principal.getName());
       if(opt.isPresent()){
           Optional<Patrimoine> optional = this.service.find(id);
           if(optional.isPresent()){
               Patrimoine current = optional.get();
               if(current.getCreatedBy().equals(opt.get().getId())){
                   return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(current),null);
               }
               return new ApiResponse<>(Boolean.FALSE,null,null,null);
           }
           return new ApiResponse<>(Boolean.FALSE,null,null,null);
       }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }

    @GetMapping("/exists/{code}")
    public ApiResponse<Boolean> exists(String code, Principal principal) {
        return null;
    }

    @PostMapping("/")
    public ApiResponse<PatrimoineDTO> save(PatrimoineDTO form, Principal principal) {
        return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(this.service.save(mapper.toEntity(form))),null);
    }

    @PostMapping("/save")
    public ApiResponse<PatrimoineDTO> saveWithFile(
                                                    @RequestPart("object") String object,
                                                    @RequestPart(value = "images",required = false) List<MultipartFile> images,
                                                    @RequestPart(value = "geoJson",required = false) MultipartFile geoJson,
                                                    Principal principal) throws JsonProcessingException {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            PatrimoineDTO form = this.objectMapper.readValue(object,PatrimoineDTO.class);
            List<String> messages = this.validator.isValid(form);
            if(messages.isEmpty()){
                Patrimoine entity = this.mapper.toEntity(form);
                if(geoJson!=null){
                    entity.setGeoJson(this.documentService.save(Document
                            .builder()
                            .uniqueCode(StringGeneratorCode.generateRandomString(40))
                            .path(this.fileStorageService.uplodeCustomName(geoJson,"/geo_json",String.format("sigma_%s.%s",new Date().getTime(),this.fileStorageService.getExtension(geoJson.getOriginalFilename()).orElseGet(null))))
                            .filename(geoJson.getOriginalFilename())
                            .label("GEO JSON")
                            .isNotified(Boolean.FALSE)
                            .build()));
                }
                if(entity.getPatrimoineAttacheds()!=null){
                    Patrimoine finalEntity1 = entity;
                    entity.setPatrimoineAttacheds(entity.getPatrimoineAttacheds().stream().peek(e->e.setPatrimoine(finalEntity1)).collect(Collectors.toSet()));
                }
                entity.getContract().getDocument().setTypeDocument(null);
                entity.setCreatedBy(optional.get().getId());
                entity.setIsDeleted(Boolean.FALSE);
                entity.getContract().setIsNotified(Boolean.FALSE);
                entity.setIsNotified(Boolean.FALSE);
                entity.setStatus(Status.BROUILLON);
                entity = this.service.save(entity);
                if(images!=null && !images.isEmpty() && form.getPhotos()!=null){
                    List<Document> documents = form.getPhotos().stream().map(documentMapper::toEntity).peek(e->e.setTypeDocument(null)).collect(Collectors.toList());
                    if(!documents.isEmpty()){
                        Patrimoine finalEntity = entity;
                        entity.setPhotos(new HashSet<>(this.documentService.saveAllWidthUpload(documents.stream().peek(e->e.setPatrimoine(finalEntity)).collect(Collectors.toList()), images, "/images")));
                        entity = this.service.save(entity);
                    }
                }
                if(entity.getTags()!=null){
                    Patrimoine finalEntity1 = entity;
                    entity.setTags(entity.getTags().stream().peek(e->e.setPatrimoines(Collections.singleton(finalEntity1))).collect(Collectors.toSet()));
                    entity = this.service.save(entity);
                }
                return new ApiResponse<>(Boolean.TRUE,null,this.mapper.toDTO(entity),null);
            }
            return new ApiResponse<>(Boolean.FALSE, messages,null,null);
        }
        return new ApiResponse<>(Boolean.FALSE, null,null,null);
    }

    @PostMapping("/update/{id}")
    public ApiResponse<PatrimoineDTO> updateWithFile(
            @PathVariable  Long id,
            @RequestPart("object") String object,
            @RequestPart(value = "images",required = false) List<MultipartFile> images,
            @RequestPart(value = "geoJson",required = false) MultipartFile geoJson,
            @RequestPart(value = "documents",required = false) List<MultipartFile> documents,
            @RequestPart(value = "contract",required = false) MultipartFile contract,
            Principal principal) throws JsonProcessingException {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            Optional<Patrimoine> opt = this.service.find(id);
            if(opt.isPresent()){
                PatrimoineDTO form = this.objectMapper.readValue(object,PatrimoineDTO.class);
                List<String> messages = this.validator.isValid(form);
                if(messages.isEmpty()){
                    Patrimoine entity = this.mapper.toEntity(form);
                    if(geoJson!=null){
                        entity.setGeoJson(this.documentService.save(Document
                                .builder()
                                .id(opt.get().getGeoJson() != null && opt.get().getGeoJson().getId()!=null ? opt.get().getGeoJson().getId() : null)
                                .uniqueCode(StringGeneratorCode.generateRandomString(40))
                                .path(this.fileStorageService.uplodeCustomName(geoJson,"/geo_json",String.format("sigma_%s.%s",new Date().getTime(),this.fileStorageService.getExtension(geoJson.getOriginalFilename()).orElseGet(null))))
                                .filename(geoJson.getOriginalFilename())
                                .label("GEO JSON")
                                .build()));
                    }
                        entity.getContract().setDocument(this.documentService.save(Document
                                .builder()
                                .id(entity.getContract().getDocument()!=null && entity.getContract().getDocument().getId()!=null?entity.getContract().getDocument().getId() : null)
                                .uniqueCode(StringGeneratorCode.generateRandomString(40))
                                .path(contract!=null?this.fileStorageService.uplodeCustomName(contract,"/contract",String.format("sigma_%s.%s",new Date().getTime(),this.fileStorageService.getExtension(contract.getOriginalFilename()).orElseGet(null))):null)
                                .filename(contract!=null?contract.getOriginalFilename():null)
                                .label("CONTRACT")
                                .isNotified(Boolean.FALSE)
                                .typeDocument(entity.getContract().getDocument().getTypeDocument())
                                .build()));

                    if(documents!=null && entity.getDocuments()!=null){
                        entity.setDocuments(new HashSet<>(this.documentService.saveAllWidthUpload(entity.getDocuments().stream().peek(e->e.setPatrimoineDocument(Patrimoine.builder().id(id).build())).collect(Collectors.toList()), documents, "/documents")));
                    }
                    if(images!=null && !images.isEmpty() && form.getPhotos()!=null){
                        List<Document> list = form.getPhotos().stream().map(documentMapper::toEntity).collect(Collectors.toList());
                        if(!list.isEmpty()){
                            entity.setPhotos(new HashSet<>(this.documentService.saveAllWidthUpload(list.stream().peek(e->e.setPatrimoine(Patrimoine.builder().id(id).build())).collect(Collectors.toList()), images, "/images")));
                        }
                    }
                    if(entity.getTags()!=null){
                        Patrimoine finalEntity1 = entity;
                        entity.setTags(entity.getTags().stream().peek(e->e.setPatrimoines(Collections.singleton(finalEntity1))).collect(Collectors.toSet()));
                    }
                    if(entity.getPatrimoineAttacheds()!=null){
                        Patrimoine finalEntity1 = entity;
                        entity.setPatrimoineAttacheds(entity.getPatrimoineAttacheds().stream().peek(e->e.setPatrimoine(finalEntity1)).collect(Collectors.toSet()));
                    }
                    entity.setCreatedBy(optional.get().getId());
                    entity.setIsDeleted(Boolean.FALSE);
                    entity = this.service.update(id,entity);
                    return new ApiResponse<>(Boolean.TRUE,null,this.mapper.toDTO(entity),null);
                }
                return new ApiResponse<>(Boolean.FALSE, messages,null,null);
            }
            return new ApiResponse<>(Boolean.FALSE, null,null,null);
        }
        return new ApiResponse<>(Boolean.FALSE, null,null,null);
    }

    @GetMapping("chang-status/{id}")
    public ApiResponse<PatrimoineDTO> updateStatus(@PathVariable Long id, Principal principal) {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            Optional<Patrimoine> patrimoineOptional = this.service.find(id);
            if(patrimoineOptional.isPresent()){
                Patrimoine current = patrimoineOptional.get();
                if(Objects.equals(current.getCreatedBy(), optional.get().getId())){
                    current.setStatus(Status.ENREGISTRER);
                    current = this.service.save(current);
                    return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(current),null);
                }
                return new ApiResponse<>(Boolean.FALSE,null,null,null);
            }
            return new ApiResponse<>(Boolean.FALSE,null,null,null);
        }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }


    @GetMapping("contact-has-file/{id}")
    public ApiResponse<Boolean> contactHasFile(@PathVariable Long id, Principal principal) {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            Optional<Patrimoine> patrimoineOptional = this.service.find(id);
            if(patrimoineOptional.isPresent()){
                Patrimoine current = patrimoineOptional.get();
                if(Objects.equals(current.getCreatedBy(), optional.get().getId())){
                    return new ApiResponse<>(Boolean.TRUE,null,current.getContract() != null &&
                            current.getContract().getDocument() != null &&
                            current.getContract().getDocument().getPath() != null,null);
                }
                return new ApiResponse<>(Boolean.FALSE,null,null,null);
            }
            return new ApiResponse<>(Boolean.FALSE,null,null,null);
        }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(@PathVariable Long id, Principal principal) {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            Optional<Patrimoine> optionalPatrimoine = this.service.find(id);
            if(optionalPatrimoine.isPresent()){
                Patrimoine current = optionalPatrimoine.get();
                if(Objects.equals(current.getCreatedBy(), optional.get().getId())){
                    return new ApiResponse<>(Boolean.TRUE,null, this.service.archived(current),null);
                }
                return new ApiResponse<>(Boolean.FALSE,Collections.singletonList("UNAUTHORIZED"),null,null);
            }
            return new ApiResponse<>(Boolean.FALSE,null,null,null);
        }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }




    @GetMapping("/geo/{id}")
    public ApiResponse<?> exportGeo(@PathVariable Long id, Principal principal) throws IOException {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            Optional<Patrimoine> opt = this.service.find(id);
            if(opt.isPresent()){
                Patrimoine current = opt.get();
                if(current.getGeoJson()!=null && StringUtils.hasText(current.getGeoJson().getPath())){
                    JsonNode rootNode = objectMapper.readTree(new File(current.getGeoJson().getPath()));
                    return new ApiResponse<>(Boolean.TRUE,null,rootNode,null);
                }
                return new ApiResponse<>(Boolean.FALSE,Collections.singletonList("LocationNOtFound"),null,null);
            }
            return new ApiResponse<>(Boolean.FALSE,Collections.singletonList("NotFound"),null,null);
        }
        return new ApiResponse<>(Boolean.FALSE,Collections.singletonList("UserNotFound"),null,null);
    }


    @GetMapping("/geos")
    public ApiResponse<?> exportGeos(Principal principal) throws IOException {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            List<Patrimoine> patrimoineList = this.service.findAllByCreatedBy(optional.get().getId());
            if(!patrimoineList.isEmpty()){
                List<FeaturesDTO> features = new ArrayList<>();
                patrimoineList.forEach(e->{
                    if(e.getGeoJson()!=null && StringUtils.hasText(e.getGeoJson().getPath())){
                        try {
                            JsonNode rootNode = objectMapper.readTree(new File(e.getGeoJson().getPath()));
                            if(rootNode.get("features")!=null && !rootNode.get("features").isEmpty()){
                                for (JsonNode obj: rootNode.get("features")) {
                                    JsonNode coordinates;
                                    String type = obj.get("geometry").get("type").asText();
                                    if(obj.get("geometry")!=null && type.equals("Polygon")){
                                        coordinates =  obj.get("geometry").get("coordinates").get(0).get(0);
                                    }else{
                                        coordinates =  obj.get("geometry").get("coordinates").get(0);
                                    }
                                    features.add(FeaturesDTO
                                            .builder()
                                            .id(e.getId())
                                            .coordinates(coordinates)
                                            .build());
                                }
                            }
                            if(rootNode.get("type").asText().equals("Feature")){
                                    JsonNode coordinates;
                                    String type = rootNode.get("geometry").get("type").asText();
                                    if(rootNode.get("geometry")!=null && type.equals("Polygon")){
                                        coordinates =  rootNode.get("geometry").get("coordinates").get(0).get(0);
                                    }else{
                                        coordinates =  rootNode.get("geometry").get("coordinates").get(0);
                                    }
                                    features.add(FeaturesDTO
                                            .builder()
                                            .id(e.getId())
                                            .coordinates(coordinates)
                                            .build());

                            }

                          //FeaturesDTO features =  objectMapper.readValue(rootNode.get("features"),FeaturesDTO.class);
                        } catch (IOException ex) {
                           log.error("cant to read this file");
                        }
                    }
                });
                return new ApiResponse<>(Boolean.TRUE,null,features,null);
                //return new ApiResponse<>(Boolean.FALSE,Collections.singletonList("LocationNOtFound"),null,null);
            }
            return new ApiResponse<>(Boolean.FALSE,Collections.singletonList("NotFound"),null,null);
        }
        return new ApiResponse<>(Boolean.FALSE,Collections.singletonList("UserNotFound"),null,null);
    }
}
