package hameed.irrigationsystem.controllers;

import hameed.irrigationsystem.exceptions.SensorInactiveException;
import hameed.irrigationsystem.models.Plot;
import hameed.irrigationsystem.models.PlotConfiguration;
import hameed.irrigationsystem.models.TimeSlot;
import hameed.irrigationsystem.services.config.ConfigurationService;
import hameed.irrigationsystem.services.integration.IntegrationService;
import hameed.irrigationsystem.services.plot.PlotService;
import hameed.irrigationsystem.services.timeslot.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plots")
public class IrrigationRestController {

    private final PlotService plotService;
    private final TimeSlotService timeSlotService;
    private final ConfigurationService configurationService;
    private final IntegrationService integrationService;

    @Autowired
    public IrrigationRestController(PlotService plotService, TimeSlotService timeSlotService,
                                    ConfigurationService configurationService, IntegrationService integrationService) {
        this.plotService = plotService;
        this.timeSlotService = timeSlotService;
        this.configurationService = configurationService;
        this.integrationService = integrationService;
    }

    @PostMapping("")
    public ResponseEntity<String> addPlot(@RequestBody Plot plot) {
        plotService.save(plot);
        return ResponseEntity.ok("Plot of Land Added Successfully");
    }

    @GetMapping("")
    public List<Plot> getAllPlots() {
        return plotService.findAll();
    }

    @PutMapping("/{plotId}")
    public ResponseEntity<String> editPlot(@PathVariable Integer plotId, @RequestBody Plot newPlot) {
        Plot plot = plotService.findById(plotId);
        plot.setName(newPlot.getName());
        plot.setArea(newPlot.getArea());
        plotService.save(plot);
        return ResponseEntity.ok("Plot of Land Updated Successfully");
    }

    @PostMapping("/{plotId}/configurations")
    public ResponseEntity<String> configurePlot(@PathVariable Integer plotId, @RequestBody PlotConfiguration newConfiguration) {
        Plot plot = plotService.findById(plotId);

        timeSlotService.save(newConfiguration.getTimeSlot());
        configurationService.save(newConfiguration);
        plot.setPlotConfiguration(newConfiguration);
        plotService.save(plot);

        return ResponseEntity.ok("Plot of Land Configured Successfully");
    }


    // the following endpoints for the automatic irrigation system
    @GetMapping("/{plotId}/irrigate")
    public ResponseEntity<Void> irrigatePlot(@PathVariable Integer plotId) {
        int maxRetryAttempts = 5;
        int retryCount = 0;
        boolean irrigationSuccess = false;

        while (!irrigationSuccess && retryCount < maxRetryAttempts) {
            irrigationSuccess = integrationService.irrigate(plotId);
            retryCount++;
        }
        if (irrigationSuccess) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/plots/" + plotId + "/irrigate/update");
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
        } else {
            throw new SensorInactiveException("Sensor dedicated for the plot with id: " + plotId + " is not active at the moment");
        }
    }

    @GetMapping("/{plotId}/irrigate/update")
    public ResponseEntity<String> updateSlot(@PathVariable Integer plotId) {
        Plot plot = plotService.findById(plotId);
        PlotConfiguration plotConfiguration = plot.getPlotConfiguration();
        TimeSlot timeSlot = plot.getPlotConfiguration().getTimeSlot();

        timeSlot.setStatus("Not Reserved");
        plotConfiguration.setTimeSlot(null);

        timeSlotService.save(timeSlot);
        configurationService.save(plotConfiguration);
        plotService.save(plot);

        return ResponseEntity.ok("Plot irrigation request was sent successfully and irrigation done!");

    }
}
