package hameed.irrigationsystem.repositories;

import hameed.irrigationsystem.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
}
