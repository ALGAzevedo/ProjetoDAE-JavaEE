package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

public class BiomedicalIndicatorQuantitativeCreateDTO extends BiomedicalIndicatorCreateDTO<Double> {
    private Double min;
    private Double max;

    public BiomedicalIndicatorQuantitativeCreateDTO(String name, String unity, Double min, Double max) {
        super(name, unity);
        this.min = min;
        this.max = max;
    }

    public BiomedicalIndicatorQuantitativeCreateDTO() {

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
