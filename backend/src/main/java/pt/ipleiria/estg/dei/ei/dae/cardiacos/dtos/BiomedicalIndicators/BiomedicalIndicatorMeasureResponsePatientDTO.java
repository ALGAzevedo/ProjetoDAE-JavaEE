package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.DTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;

import java.time.LocalDate;

public class BiomedicalIndicatorMeasureResponsePatientDTO<T> implements DTO {
    private Long id;
    private String date;
    private T value;
    private String indicator;
    private String description;

    public BiomedicalIndicatorMeasureResponsePatientDTO() {
    }

    public BiomedicalIndicatorMeasureResponsePatientDTO(Long id, LocalDate date, T value, String indicator, String description) {
        this.id = id;
        this.date = date.toString();
        this.value = value;
        this.indicator = indicator;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }
}
