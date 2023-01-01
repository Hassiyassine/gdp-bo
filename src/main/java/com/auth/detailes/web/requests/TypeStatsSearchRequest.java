package com.auth.detailes.web.requests;

import com.auth.detailes.utilities.enums.Status;
import lombok.Data;

@Data
public class TypeStatsSearchRequest extends  SearchRequest{

    private String label;

    private String code;

    private Long collecteId;

    private Long produitId;

    private Status statusProduit;

    private Status statusCollecte;

    private String typeStats;


}
