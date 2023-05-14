package hameed.irrigationsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_devices")
public class SensorDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "plot_id")
    private Plot plot;

    @Column(name = "status", nullable = false)
    private String status;

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Plot getPlot() {
        return plot;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SensorDevice{" +
                "sensorId=" + id +
                ", plot=" + plot +
                ", status='" + status + '\'' +
                '}';
    }
}
