package hameed.irrigationsystem.repositories;

import hameed.irrigationsystem.models.PlotConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfigurationRepository extends JpaRepository<PlotConfiguration, Integer> {
}
