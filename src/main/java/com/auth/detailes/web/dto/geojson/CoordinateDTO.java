package com.auth.detailes.web.dto.geojson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;


@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class CoordinateDTO {


    private List<Objects> objects;
}
