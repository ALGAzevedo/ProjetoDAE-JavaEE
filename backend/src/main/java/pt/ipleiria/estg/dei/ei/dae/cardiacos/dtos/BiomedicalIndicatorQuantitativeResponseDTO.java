package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

public class BiomedicalIndicatorQuantitativeResponseDTO extends BiomedicalIndicatorResponseDTO<Double> {
    private Double min;
    private Double max;

    public BiomedicalIndicatorQuantitativeResponseDTO(long id, String name, String unity, Double min, Double max) {
        super(id, name, unity);
        this.min = min;
        this.max = max;
    }

    public BiomedicalIndicatorQuantitativeResponseDTO() {

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
