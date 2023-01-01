package com.auth.detailes.web.dto.geojson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class GeometryDTO {


   // private Long id;
    private String type;
    private List<Objects> coordinates;

}
