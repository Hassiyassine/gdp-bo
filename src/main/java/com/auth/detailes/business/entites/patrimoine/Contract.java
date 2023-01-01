package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.DateAudit;
import com.auth.detailes.business.entites.common.Document;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dateStart")
    private Date dateStart;

    @Column(name="dateEnd")
    private Date dateEnd;

    @Column(name="proprietaire")
    private String proprietaire;

    @Column(name="tauxaugmentation")
    private String tauxaugmentation;

    @Column(name="delaisAugmentation")
    private String delaisAugmentation;

    @Column(name="locataire")
    private String locataire;

    @Column(name="loyer")
    private String loyer;

    @Column(name="dateButtoire")
    private Date dateButtoire;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Document document;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToOne
    private Patrimoine patrimoine;

    @Column(name = "isNotified")
    private Boolean isNotified;
}
