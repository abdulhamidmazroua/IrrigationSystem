package hameed.irrigationsystem.repositories;

import hameed.irrigationsystem.models.SensorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<SensorDevice, Integer> {

    Optional<SensorDevice> findByPlotId(Integer plotId);

}
