package com.auth.detailes.web.api.secure;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.entites.patrimoine.Notification;
import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.service.mpl.NotificationService;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.EndPointConstent;
import com.auth.detailes.web.dto.NotificationDTO;
import com.auth.detailes.web.dto.PatrimoineDTO;
import com.auth.detailes.web.mappers.NotificationMapper;
import com.auth.detailes.web.requests.NotificationSearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping(EndPointConstent.NOTIFICATION_ENDPOINT_REST)
@Slf4j
public class SecureNotificationRestController extends CustomRestController {

    private final NotificationService service;
    private final NotificationMapper mapper;


    @GetMapping
    public ApiResponse<Page<?>> findAllByCriteria(NotificationSearchRequest searchRequest, Principal principal) {
        Optional<User> optional = this.userService.findAccountByLogin(principal.getName());
        if(optional.isPresent()){
            searchRequest.setCreateBy(optional.get().getId());
            Page<Notification> page = this.service.findAll(searchRequest, PageRequest.of(searchRequest.getPage(),searchRequest.getSize()));
            return new ApiResponse<>(Boolean.TRUE,null,page.map(mapper::toDTO),null);
        }
        return new ApiResponse<>(Boolean.FALSE,Collections.singletonList(""),null,null);
    }

    @GetMapping("/{id}")
    public ApiResponse<NotificationDTO> findOneById(@PathVariable Long id, Principal principal) {
       Optional<User> opt = this.userService.findAccountByLogin(principal.getName());
       if(opt.isPresent()){
           Optional<Notification> optional = this.service.find(id);
           if(optional.isPresent()){
               Notification current = optional.get();
                   return new ApiResponse<>(Boolean.TRUE,null,mapper.toDTO(current),null);
           }
           return new ApiResponse<>(Boolean.FALSE,null,null,null);
       }
        return new ApiResponse<>(Boolean.FALSE,null,null,null);
    }
}
