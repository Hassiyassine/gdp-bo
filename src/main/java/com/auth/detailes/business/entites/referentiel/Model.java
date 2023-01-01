package com.auth.detailes.business.entites.referentiel;

import com.auth.detailes.business.entites.common.UserDateAudit;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class Model extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long  id;

    @Column(name = "label", nullable = false)
    private String label;


    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "code", nullable = false)
    private String code;
}
