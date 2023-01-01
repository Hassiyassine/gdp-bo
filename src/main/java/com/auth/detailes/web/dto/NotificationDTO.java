package com.auth.detailes.web.dto;

import com.auth.detailes.web.responses.PatrimoineResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO  {

    private long id;
    private PatrimoineResponse patrimoine;
    private Date dateHomologationExpiration;
    private Date dateCertificatPropriete;
    private Date noteRenseignement;
    private Date planCadastrale;
    private Date planArchitecte;
    private Date compromisVente;
    private Date location;
    private Date dateDeliverance;
    private String typeTexe;
    private Boolean dateHomologationExpirationSend;
    private Boolean dateCertificatProprieteSend;
    private Boolean noteRenseignementSend;
    private Boolean planCadastraleSend;
    private Boolean planArchitecteSend;
    private Boolean compromisVenteSend;
    private Boolean locationSend;
    private Boolean dateDeliveranceSend;
}
