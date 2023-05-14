package hameed.irrigationsystem.services.integration;

import hameed.irrigationsystem.models.Plot;

public interface IntegrationService {

    // this method used to simulate the irrigation
    boolean irrigate(Integer plotId);
}
