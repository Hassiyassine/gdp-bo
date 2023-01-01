package com.auth.detailes.web.mappers;

import com.auth.detailes.business.entites.patrimoine.Notification;
import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.web.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotificationMapper extends GenericMapper<Notification, NotificationDTO> {


    @Autowired
   private PatrimoineMapper mapper;

    @Override
    public Notification toEntity(NotificationDTO dto) {
        return  Notification.builder()
                .id(dto.getId())
                .compromisVente(dto.getCompromisVente())
                .dateHomologationExpiration(dto.getDateHomologationExpiration())
                .compromisVenteSend(dto.getCompromisVenteSend())
                .dateCertificatPropriete(dto.getDateCertificatPropriete())
                .dateCertificatProprieteSend(dto.getDateCertificatProprieteSend())
                .dateDeliverance(dto.getDateDeliverance())
                .dateDeliveranceSend(dto.getDateDeliveranceSend())
                .dateHomologationExpirationSend(dto.getDateHomologationExpirationSend())
                .location(dto.getLocation())
                .locationSend(dto.getLocationSend())
                .noteRenseignement(dto.getNoteRenseignement())
                .noteRenseignementSend(dto.getNoteRenseignementSend())
                .patrimoine(dto.getPatrimoine()!=null && dto.getPatrimoine().getId()!=null? Patrimoine.builder().id(dto.getPatrimoine().getId()).build() : null)
                .planArchitecte(dto.getPlanArchitecte())
                .planArchitecteSend(dto.getPlanArchitecteSend())
                .planCadastrale(dto.getPlanCadastrale())
                .planCadastraleSend(dto.getPlanCadastraleSend())
                .typeTexe(dto.getTypeTexe())
                .build();
    }


    @Override
    public NotificationDTO toDTO(Notification entity) {
        return  NotificationDTO.builder()
                .id(entity.getId())
                .compromisVente(entity.getCompromisVente())
                .dateHomologationExpiration(entity.getDateHomologationExpiration())
                .compromisVenteSend(entity.getCompromisVenteSend())
                .dateCertificatPropriete(entity.getDateCertificatPropriete())
                .dateCertificatProprieteSend(entity.getDateCertificatProprieteSend())
                .dateDeliverance(entity.getDateDeliverance())
                .dateDeliveranceSend(entity.getDateDeliveranceSend())
                .dateHomologationExpirationSend(entity.getDateHomologationExpirationSend())
                .location(entity.getLocation())
                .locationSend(entity.getLocationSend())
                .noteRenseignement(entity.getNoteRenseignement())
                .noteRenseignementSend(entity.getNoteRenseignementSend())
                .patrimoine(entity.getPatrimoine()!=null && entity.getPatrimoine().getId()!=null? this.mapper.toResponse(entity.getPatrimoine()) : null)
                .planArchitecte(entity.getPlanArchitecte())
                .planArchitecteSend(entity.getPlanArchitecteSend())
                .planCadastrale(entity.getPlanCadastrale())
                .planCadastraleSend(entity.getPlanCadastraleSend())
                .typeTexe(entity.getTypeTexe())
                .build();
    }
}
