package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.entites.common.DateAudit;
import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.utilities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class LandReserve extends DateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="nom")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="descriptoin")
    private String description;

    @Column(name="zoning")
    private String zoning;

    @Column(name="usage")
    private String usage;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="registrationNumber")
    private Integer registrationNumber;

    @Column(name="approvalDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvalDate;

    @Column(name="expirationDate")
    private Date expirationDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;


    @OneToMany
    private Set<Document> documents;

    @OneToMany
    private Set<Event> events;

    @Column(name="wkt")
    private String wkt;


}
