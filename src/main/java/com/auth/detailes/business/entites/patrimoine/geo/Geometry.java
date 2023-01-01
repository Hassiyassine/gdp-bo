package com.auth.detailes.business.entites.patrimoine.geo;

import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;


@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class Geometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Coordinate> coordinates;

    @OneToOne(mappedBy = "geometry")
    private Patrimoine patrimoine;
}
