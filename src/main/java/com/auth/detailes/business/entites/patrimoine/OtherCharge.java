package com.auth.detailes.business.entites.patrimoine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtherCharge{

    @OneToOne(cascade = CascadeType.REFRESH)
    private Contract contract;
}
