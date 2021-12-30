package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import java.time.LocalDate;

public class QuantitativeBiomedicalIndicatorMeasureDTO implements DTO{
    private Long id;
    private Double value;
    private LocalDate date;
    private String description;

    public QuantitativeBiomedicalIndicatorMeasureDTO() {
    }

    public QuantitativeBiomedicalIndicatorMeasureDTO(Long id, Double value, LocalDate date, String description) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
