package com.auth.detailes.business.entites.referentiel;

import com.auth.detailes.business.entites.common.UserDateAudit;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class TypeRegim extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Long id;

    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
