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
    private long id;
    @NotNull
    private T value;
    @NotNull
    private LocalDate date;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private BiomedicalIndicator<T> indicator;



    public PatientBiomedicalIndicator(T value, LocalDate date, Patient p, BiomedicalIndicator<T> b) {
        this.value = value;
        this.date = date;
        this.patient = p;
        this.indicator = b;
    }

    public PatientBiomedicalIndicator(T value, Patient p, BiomedicalIndicator<T> b) {
        this.value = value;
        this.date = LocalDate.now();
        this.patient = p;
        this.indicator = b;
    }

    public PatientBiomedicalIndicator() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
