package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorMeasure;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;

import java.util.LinkedList;

public class BiomedicalIndicatorQuantitativeDTO extends BiomedicalIndicatorCreateDTO<Double> {
    private Double min;
    private Double max;

    public BiomedicalIndicatorQuantitativeDTO(String name, String unity, Double min, Double max) {
        super(name, unity);
        this.min = min;
        this.max = max;
    }

    public BiomedicalIndicatorQuantitativeDTO() {

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
