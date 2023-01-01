package com.auth.detailes.web.dto.geojson;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class GeojsonDTO {

    private Long id;
    private String type;
    private List<FeaturesDTO> features;

}
