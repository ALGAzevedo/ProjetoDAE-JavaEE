package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.Entity;

@Entity
public class BiomedicalIndicatorsQuantitative extends BiomedicalIndicator<Double>{
    @NotNull
    private Double min;
    @NotNull
    private Double max;

    public BiomedicalIndicatorsQuantitative() {
        super();
    }

    public BiomedicalIndicatorsQuantitative(String name, Double min, Double max) {
        super(name);
        this.min = min;
        this.max = max;
    }

    public BiomedicalIndicatorsQuantitative(String name, String unity, Double min, Double max) {
        super(name, unity);
        this.min = min;
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
