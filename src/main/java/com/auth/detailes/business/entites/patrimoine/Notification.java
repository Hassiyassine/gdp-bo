package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Patrimoine patrimoine;

    @Column(name = "dateHomologationExpiration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHomologationExpiration;

    @Column(name = "dateCertificatPropriete")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCertificatPropriete;

    @Column(name = "noteRenseignement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date noteRenseignement;

    @Column(name = "planCadastrale")
    @Temporal(TemporalType.TIMESTAMP)
    private Date planCadastrale;

    @Column(name = "planArchitecte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date planArchitecte;

    @Column(name = "compromisVente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date compromisVente;

    @Column(name = "compromisPropriete")
    @Temporal(TemporalType.TIMESTAMP)
    private Date compromisPropriete;

    @Column(name = "location")
    @Temporal(TemporalType.TIMESTAMP)
    private Date location;

    @Column(name = "dateDeliverance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeliverance;

    @Column(name = "typeTexe")
    private String typeTexe;

    @Column(name = "dateHomologationExpirationSend")
    private Boolean dateHomologationExpirationSend;

    @Column(name = "dateCertificatProprieteSend")
    private Boolean dateCertificatProprieteSend;

    @Column(name = "noteRenseignementSend")
    private Boolean noteRenseignementSend;

    @Column(name = "planCadastraleSend")
    private Boolean planCadastraleSend;

    @Column(name = "planArchitecteSend")
    private Boolean planArchitecteSend;

    @Column(name = "compromisVenteSend")
    private Boolean compromisVenteSend;

    @Column(name = "locationSend")
    private Boolean locationSend;

    @Column(name = "dateDeliveranceSend")
    private Boolean dateDeliveranceSend;

    @Column(name = "compromisProprieteSend")
    private Boolean compromisProprieteSend;
}
