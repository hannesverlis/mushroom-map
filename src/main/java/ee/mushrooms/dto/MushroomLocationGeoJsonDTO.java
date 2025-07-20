package ee.mushrooms.dto;

import ee.mushrooms.entity.MushroomLocation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MushroomLocationGeoJsonDTO {

    private Long id;


    private String type;


    private Geometry geometry;


    private Properties properties;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geometry {
        private String type;
        private List<Double> coordinates;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Properties {
        private String description;

    }
}
