package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class BiomedicalIndicatorMeasure<T> {
    @Id
    private long id;
    @NotNull
    private T value;
    @NotNull
    private Date date;

    @ManyToOne
    private BiomedicalIndicator<T> indicator;

    public BiomedicalIndicatorMeasure(T value, Date date) {
        this.value = value;
        this.date = date;
    }

    public BiomedicalIndicatorMeasure() {

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
