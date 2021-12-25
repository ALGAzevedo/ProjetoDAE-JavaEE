package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class BiomedicalIndicatorMeasure<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private T value;
    @NotNull
    private LocalDate date;


    @ManyToOne
    private BiomedicalIndicator<T> indicator;

    public BiomedicalIndicatorMeasure(T value, LocalDate date) {
        this.value = value;
        this.date = date;
    }

    public BiomedicalIndicatorMeasure(T value) {
        this.value = value;
        this.date = LocalDate.now();
    }

    public BiomedicalIndicatorMeasure() {

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
