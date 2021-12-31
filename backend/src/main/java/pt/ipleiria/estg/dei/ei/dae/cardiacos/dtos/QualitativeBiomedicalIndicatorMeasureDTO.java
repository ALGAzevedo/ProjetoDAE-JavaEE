package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class QualitativeBiomedicalIndicatorMeasureDTO implements DTO {
    private Long id;
    private String value;
    private LocalDateTime date;
    private String description;

    public QualitativeBiomedicalIndicatorMeasureDTO(Long id, String value, LocalDateTime date, String description) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.description = description;
    }

    public QualitativeBiomedicalIndicatorMeasureDTO() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
