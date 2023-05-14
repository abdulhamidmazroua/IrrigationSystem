package hameed.irrigationsystem.repositories;

import hameed.irrigationsystem.models.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository<Plot, Integer> {
}
