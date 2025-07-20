package ee.mushrooms.service;

import ee.mushrooms.dto.MushroomLocationGeoJsonDTO;
import ee.mushrooms.entity.MushroomLocation;
import ee.mushrooms.repository.MushroomLocationRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MushroomLocationService {

    private final MushroomLocationRepository repository;
    private final boolean isInsideEstonia(double lon, double lat) {
        return lon >= 23.5 && lon <= 28.29 && lat >= 57.9 && lat <= 59.5;
    }

    public MushroomLocationGeoJsonDTO saveFromGeoJson(MushroomLocationGeoJsonDTO dto) {
                List<Double> coords = dto.getGeometry().getCoordinates();
        double lon = coords.get(0);
        double lat = coords.get(1);

        String desc = dto.getProperties().getDescription();


        if (!isInsideEstonia(lon, lat)) {
            throw new IllegalArgumentException("Koordinaadid peavad jääma seene ala sisse.  lon >= 23.5 && lon <= 28.29 && lat >= 57.9 && lat <= 59.5");
        }


        Point p = new GeometryFactory(new PrecisionModel(), 4326)
                .createPoint(new Coordinate(lon, lat));


        MushroomLocation entity = new MushroomLocation(null, desc,  p);
        MushroomLocation saved = repository.save(entity);


        return toGeoJson(saved);
    }

    public List<MushroomLocationGeoJsonDTO> getAllAsGeoJson() {
        return repository.findAll().stream().map(this::toGeoJson).toList();
    }

    public MushroomLocationGeoJsonDTO updateFromGeoJson(Long id, MushroomLocationGeoJsonDTO dto) {
        MushroomLocation entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID-ga " + id + " asukohta ei leitud."));

        List<Double> coords = dto.getGeometry().getCoordinates();
        double lon = coords.get(0);
        double lat = coords.get(1);

        if (!isInsideEstonia(lon, lat)) {
            throw new IllegalArgumentException("Koordinaadid peavad jääma seene ala sisse.  lon >= 23.5 && lon <= 28.29 && lat >= 57.9 && lat <= 59.5");
        }

        String desc = dto.getProperties().getDescription();

        entity.setDescription(desc);

        entity.setLocation(
                new GeometryFactory(new PrecisionModel(), 4326)
                        .createPoint(new Coordinate(lon, lat))
        );

        return toGeoJson(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public MushroomLocationGeoJsonDTO toGeoJson(MushroomLocation entity) {

        double lon = entity.getLocation().getX();
        double lat = entity.getLocation().getY();

        MushroomLocationGeoJsonDTO.Geometry geometry = new MushroomLocationGeoJsonDTO.Geometry(
                "Point",
                List.of(lon, lat)
        );

        MushroomLocationGeoJsonDTO.Properties properties = new MushroomLocationGeoJsonDTO.Properties(
                entity.getDescription()
        );

        return new MushroomLocationGeoJsonDTO(entity.getId(), "Feature", geometry, properties);
    }
}
