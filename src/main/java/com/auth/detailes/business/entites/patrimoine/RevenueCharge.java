package com.auth.detailes.business.entites.patrimoine;


import com.auth.detailes.business.entites.common.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueCharge extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="nom")
    private String name;

    @Column(name="descriptoin")
    private String description;

    @Column(name="frequence")
    private int frequence;

    @Column(name="amount")
    private int amount;
}
