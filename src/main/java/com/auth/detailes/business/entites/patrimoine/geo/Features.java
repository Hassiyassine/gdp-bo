package com.auth.detailes.business.entites.patrimoine.geo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="type")
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    private Geometry geometry;

}
