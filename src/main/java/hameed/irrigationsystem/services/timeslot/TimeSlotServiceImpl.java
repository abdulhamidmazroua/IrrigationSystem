package hameed.irrigationsystem.services.timeslot;

import hameed.irrigationsystem.models.TimeSlot;
import hameed.irrigationsystem.repositories.TimeSlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;


    @Autowired
    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public void save(TimeSlot timeSlot) {
        timeSlotRepository.save(timeSlot);
    }

    @Override
    public TimeSlot findById(Integer timeSlotId) {
        return timeSlotRepository.findById(timeSlotId)
                .orElseThrow( () -> new EntityNotFoundException("Time Slot with id: " + timeSlotId + " - not found"));
    }
}
