package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import java.time.LocalDate;

public class QuantitativeBiomedicalIndicatorMeasureDTO implements DTO{
    private Long id;
    private Double value;
    private LocalDate date;

    public QuantitativeBiomedicalIndicatorMeasureDTO() {
    }

    public QuantitativeBiomedicalIndicatorMeasureDTO(Long id, Double value, LocalDate date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
