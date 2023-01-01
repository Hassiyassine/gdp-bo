package com.auth.detailes.web.dto.geojson;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class FeaturesDTO {

    private Long id;
    //private String type;
    private JsonNode coordinates;

}
