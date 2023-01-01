package com.auth.detailes.business.entites.patrimoine.geo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="type")
    private String type;

    @ManyToOne
    private Geometry geometry;
}
