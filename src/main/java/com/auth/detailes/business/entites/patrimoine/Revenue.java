package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class Revenue extends RevenueCharge{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="type_charge")
    private String type;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private LandReserve landReserve;

    @OneToMany
    private Set<Document> documents;


}
