package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.DateAudit;
import com.auth.detailes.business.entites.common.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "article_number")
    private int number;

    @OneToMany
    private Set<Document> documents;
}
