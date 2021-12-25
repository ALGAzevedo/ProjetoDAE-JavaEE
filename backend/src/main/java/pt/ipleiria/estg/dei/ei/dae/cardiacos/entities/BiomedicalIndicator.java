package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;

@Table(name = "BiomedicalIndicators")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class BiomedicalIndicator<T> extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String unity;

    @OneToMany(mappedBy = "id")
    private LinkedList<BiomedicalIndicatorMeasure<T>> values;

    @ManyToOne
    private Patient patient;

    //TODO REGISTO HISTORICO
    private Date deletedAt;
    private Date updatedAt;
    @OneToOne
    private BiomedicalIndicator<T> anterior;

    public BiomedicalIndicator() {
        this.values = new LinkedList<>();
    }

    public BiomedicalIndicator(String name) {
        this();
        this.name = name;

    }

    public BiomedicalIndicator(String name, String unity) {
        this();
        this.name = name;
        this.unity = unity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public LinkedList<BiomedicalIndicatorMeasure<T>> getValues() {
        return new LinkedList<>(values);
    }

    public void setValues(LinkedList<BiomedicalIndicatorMeasure<T>> values) {
        this.values = values;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void add(BiomedicalIndicatorMeasure<T> measure) {
        if(measure != null) {
            values.add(measure);
        }
    }
}
