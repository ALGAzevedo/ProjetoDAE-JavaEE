package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

@Table(name = "PatientsBiomedicalIndicators")
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPatientBiomedicalIndicators",
                query = "SELECT s FROM PatientBiomedicalIndicator s ORDER BY s.date desc" // JPQL
        ),
})
public class PatientBiomedicalIndicator<T> extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private T value;
    @NotNull
    private LocalDateTime date;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private BiomedicalIndicator<T> indicator;

    private String description;



    public PatientBiomedicalIndicator(T value, LocalDateTime date, Patient p, BiomedicalIndicator<T> b, String description) {
        this.value = value;
        this.date = date;
        this.patient = p;
        this.indicator = b;
        this.description = description;
    }

    public PatientBiomedicalIndicator(T value, Patient p, BiomedicalIndicator<T> b, String description) {
        this.value = value;
        this.date = LocalDateTime.now();
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientBiomedicalIndicator<?> indicator = (PatientBiomedicalIndicator<?>) o;
        return Objects.equals(id, indicator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "PatientBiomedicalIndicator{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                ", patient=" + patient +
                ", indicator=" + indicator +
                ", description='" + description + '\'' +
                '}';
    }
}
