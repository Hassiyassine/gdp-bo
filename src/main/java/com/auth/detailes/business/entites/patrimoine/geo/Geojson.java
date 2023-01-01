package com.auth.detailes.business.entites.patrimoine.geo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class Geojson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Features> features;

}
