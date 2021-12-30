package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "PatientsBiomedicalIndicators")
@Entity
public class PatientBiomedicalIndicator<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private T value;
    @NotNull
    private LocalDate date;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private BiomedicalIndicator<T> indicator;

    private String description;



    public PatientBiomedicalIndicator(T value, LocalDate date, Patient p, BiomedicalIndicator<T> b, String description) {
        this.value = value;
        this.date = date;
        this.patient = p;
        this.indicator = b;
        this.description = description;
    }

    public PatientBiomedicalIndicator(T value, Patient p, BiomedicalIndicator<T> b, String description) {
        this.value = value;
        this.date = LocalDate.now();
        this.patient = p;
        this.indicator = b;
        this.description = description;
    }

    public PatientBiomedicalIndicator() {

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public BiomedicalIndicator<T> getIndicator() {
        return indicator;
    }

    public void setIndicator(BiomedicalIndicator<T> indicator) {
        this.indicator = indicator;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
