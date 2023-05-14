package hameed.irrigationsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "plots")
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "area", nullable = false)
    private float area;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "configuration_id")
    private PlotConfiguration plotConfiguration;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public PlotConfiguration getPlotConfiguration() {
        return plotConfiguration;
    }

    public void setPlotConfiguration(PlotConfiguration plotConfiguration) {
        this.plotConfiguration = plotConfiguration;
    }

    @Override
    public String toString() {
        return "Plot{" +
                "plotId=" + id +
                ", name=" + name +
                ", area=" + area +
                '}';
    }
}
