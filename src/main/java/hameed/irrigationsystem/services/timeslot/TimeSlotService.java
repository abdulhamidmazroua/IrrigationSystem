package hameed.irrigationsystem.services.timeslot;

import hameed.irrigationsystem.models.TimeSlot;


public interface TimeSlotService {

    void save(TimeSlot timeSlot);

    TimeSlot findById(Integer timeSlotId);
}
