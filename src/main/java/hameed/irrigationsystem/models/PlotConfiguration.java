package hameed.irrigationsystem.models;



import jakarta.persistence.*;


@Entity
@Table(name = "plot_configuration")
public class PlotConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "water_required", nullable = false)
    private float waterRequired;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;


    public int getId() {
        return id;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public float getWaterRequired() {
        return waterRequired;
    }

    public void setWaterRequired(float waterRequired) {
        this.waterRequired = waterRequired;
    }
}
