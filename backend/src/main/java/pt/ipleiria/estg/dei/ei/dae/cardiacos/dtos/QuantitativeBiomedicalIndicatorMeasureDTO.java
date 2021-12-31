package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuantitativeBiomedicalIndicatorMeasureDTO implements DTO{
    private Long id;
    private Double value;
    private LocalDateTime date;
    private String description;

    public QuantitativeBiomedicalIndicatorMeasureDTO() {
    }

    public QuantitativeBiomedicalIndicatorMeasureDTO(Long id, Double value, LocalDateTime date, String description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:m:s");
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:m:s");
        this.date = date;
    }



}
