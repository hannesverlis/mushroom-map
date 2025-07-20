package ee.mushrooms.repository;

import ee.mushrooms.entity.MushroomLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MushroomLocationRepository extends JpaRepository<MushroomLocation, Long> {
}
