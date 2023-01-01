package com.auth.detailes.business.entites.patrimoine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class TypeRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="label")
    private String label;
    @Column(name="code")
    private String code;
    @Column(name="description")
    private String description;
}
