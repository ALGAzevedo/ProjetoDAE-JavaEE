package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.DTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BiomedicalIndicatorMeasureResponsePatientDTO<T> implements DTO {
    private Long id;
    private String date;
    private T value;
    private String indicator;
    private String description;
    private String indicatorType;
    private Long indicatorId;
    public BiomedicalIndicatorMeasureResponsePatientDTO() {
    }

    public BiomedicalIndicatorMeasureResponsePatientDTO(Long id, LocalDateTime date, T value,
                                                        String indicator, String description, String indicatorType, Long indID) {
        this.id = id;
        this.date = date.toString();
        this.value = value;
        this.indicator = indicator;
        this.description = description;
        this.indicatorType = indicatorType;
        this.indicatorId = indID;
    }

    public Long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
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

    public String getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
    }
}
