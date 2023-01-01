package com.auth.detailes.utilities.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum LandReserveStatus {

    REGISTERED("Immatriculé"),
    REQUISITION("Réquisition"),
    NI("NI");

    private String name;
}
