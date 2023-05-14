package hameed.irrigationsystem.services.integration;

import hameed.irrigationsystem.models.SensorDevice;
import hameed.irrigationsystem.repositories.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegrationServiceImpl implements IntegrationService{

    private final SensorRepository sensorRepository;

    @Autowired
    public IntegrationServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean irrigate(Integer plotId) {
        // check if the sensor dedicated for irrigating this plot of land is active
        SensorDevice sensor = sensorRepository.findByPlotId(plotId)
                .orElseThrow( () -> new EntityNotFoundException("Plot with id: " + plotId + " - not found"));
        if (sensor.getStatus().equals("Active")){
            // simulating the irrigation process
            return true;
        } else {
            // sensor is inactive and irrigation cannot be initiated
            return false;
        }
    }
}
