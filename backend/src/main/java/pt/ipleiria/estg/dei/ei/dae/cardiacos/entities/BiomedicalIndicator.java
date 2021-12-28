package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, length=100)
@Table(name = "BiomedicalIndicators")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BiomedicalIndicator<T> extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String unity;

    @OneToMany(mappedBy = "indicator", cascade = CascadeType.PERSIST)
    private List<PatientBiomedicalIndicator<T>> values;


    //TODO REGISTO HISTORICO
    private LocalDate deletedAt;
    private LocalDate updatedAt;

    @OneToOne(cascade = CascadeType.PERSIST)
    private BiomedicalIndicator<T> previous;



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

    public LinkedList<PatientBiomedicalIndicator<T>> getValues() {
        return new LinkedList<>(values);
    }

    public void setValues(LinkedList<PatientBiomedicalIndicator<T>> values) {
        this.values = values;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void add(PatientBiomedicalIndicator<T> measure) {
        if(measure != null) {
            values.add(measure);
        }
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BiomedicalIndicator<T> getPrevious() {
        return previous;
    }

    public void setPrevious(BiomedicalIndicator<T> previous) {
        this.previous = previous;
    }
}
