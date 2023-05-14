package hameed.irrigationsystem.services.config;

import hameed.irrigationsystem.models.PlotConfiguration;
import hameed.irrigationsystem.repositories.ConfigurationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService{

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationServiceImpl(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public void save(PlotConfiguration plotConfiguration) {
        configurationRepository.save(plotConfiguration);
    }

    @Override
    public PlotConfiguration findById(Integer configurationId) {
        return configurationRepository.findById(configurationId)
                .orElseThrow( () -> new EntityNotFoundException("Plot Configuration with id: " + " - not found"));
    }


}
