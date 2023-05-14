package hameed.irrigationsystem.services.plot;

import hameed.irrigationsystem.models.Plot;
import hameed.irrigationsystem.repositories.PlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotServiceImpl implements PlotService{

    private final PlotRepository plotRepository;

    @Autowired
    public PlotServiceImpl(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }


    @Override
    public void save(Plot plot) {
        plotRepository.save(plot);
    }

    @Override
    public List<Plot> findAll() {
        return plotRepository.findAll();
    }

    @Override
    public Plot findById(Integer plotId) {
        return plotRepository.findById(plotId).
                orElseThrow(() -> new EntityNotFoundException("Plot with id: " + plotId + " - not found"));
    }

    @Override
    public void deleteById(Integer plotId) {
        plotRepository.deleteById(plotId);
    }
}
