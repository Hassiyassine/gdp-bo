package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.Document;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "patrimoine", referencedColumnName = "id")
    private LandReserve landReserve;

    @Column(name="type_charge")
    private String type;

    @OneToMany()
    private Set<Document> documents;

    @OneToMany()
    private List<Event> events;
}
