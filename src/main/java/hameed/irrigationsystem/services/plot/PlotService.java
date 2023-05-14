package hameed.irrigationsystem.services.plot;

import hameed.irrigationsystem.models.Plot;

import java.util.List;

public interface PlotService {

    // for editing and creating
    void save(Plot plot);

    List<Plot> findAll();

    Plot findById(Integer plotId);

    void deleteById(Integer plotId);
}
