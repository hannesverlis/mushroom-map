package ee.mushrooms.controller;

import ee.mushrooms.dto.MushroomLocationGeoJsonDTO;
import ee.mushrooms.service.MushroomLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mushrooms")
@RequiredArgsConstructor
public class MushroomLocationController {

    private final MushroomLocationService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody MushroomLocationGeoJsonDTO dto) {
        return ResponseEntity.ok(service.saveFromGeoJson(dto));
    }

    @GetMapping
    public List<MushroomLocationGeoJsonDTO> getAll() {
        return service.getAllAsGeoJson();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MushroomLocationGeoJsonDTO dto) {
        return ResponseEntity.ok(service.updateFromGeoJson(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
