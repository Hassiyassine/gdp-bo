package com.auth.detailes.business.entites.common;

import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.business.entites.referentiel.TypeDocument;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Slf4j
@Data
public class Document extends UserDateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Long id;

    @Column(name = "label")
    private String label;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "path")
    private String path;

    @Column(name = "filename")
    private String filename;

    @Column(name = "extension")
    private String extension;

    @Column(name = "taille")
    private String tail;

    @Column(name = "isPrimary")
    private Boolean isPrimary;

    @Column(name = "uniqueCode")
    private String uniqueCode;

    @Column(name = "dateDel")
    private Date dateDel;

    @Column(name = "date_caducit")
    private Date date_caducit;

    @Column(name = "isNotified")
    private Boolean isNotified = Boolean.TRUE;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH)
    private TypeDocument typeDocument;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Patrimoine patrimoine;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Patrimoine patrimoineDocument;
}
