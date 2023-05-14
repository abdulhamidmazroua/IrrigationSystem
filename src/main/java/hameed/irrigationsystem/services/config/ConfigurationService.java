package hameed.irrigationsystem.services.config;

import hameed.irrigationsystem.models.PlotConfiguration;

public interface ConfigurationService {

    void save(PlotConfiguration plotConfiguration);

    PlotConfiguration findById(Integer configurationId);

}
