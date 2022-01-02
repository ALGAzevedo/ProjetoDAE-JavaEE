package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, length=100)
@Table(name = "BiomedicalIndicators")
@NamedQueries({
        @NamedQuery(
                name = "FindWithName",
                query = "SELECT s FROM BiomedicalIndicator s WHERE UPPER(s.name) = UPPER(:name)" // JPQL
        ),
        @NamedQuery(
                name = "FindWithNameWithoutTrashed",
                query = "SELECT s FROM BiomedicalIndicator s WHERE UPPER(s.name) = UPPER(:name) AND s.deletedAt IS NULL" // JPQL
        ),
        @NamedQuery(
                name = "getAllBiomedicalIndicators",
                query = "SELECT s FROM BiomedicalIndicator s WHERE s.deletedAt IS NULL ORDER BY s.id desc" // JPQL
        ),
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BiomedicalIndicator<T> extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String unity;

    @OneToMany(mappedBy = "indicator", cascade = CascadeType.PERSIST)
    private List<PatientBiomedicalIndicator<T>> patientIndicatorValues;

    @NotNull
    private String indicatorType;

    private LocalDate deletedAt;
    private LocalDate updatedAt;

    @OneToOne(cascade = CascadeType.PERSIST)
    private BiomedicalIndicator<T> previous;



    public BiomedicalIndicator() {
        this.patientIndicatorValues = new LinkedList<>();
    }

    public BiomedicalIndicator(String name, String indicatorType) {
        this();
        this.name = name;
        this.indicatorType = indicatorType;
        this.patientIndicatorValues = new LinkedList<>();
    }


    public BiomedicalIndicator(String name, String unity, String indicatorType) {
        this();
        this.name = name;
        this.unity = unity;
        this.indicatorType = indicatorType;
        this.patientIndicatorValues = new LinkedList<>();
    }

    public void setPatientIndicatorValues(List<PatientBiomedicalIndicator<T>> values) {
        this.patientIndicatorValues = values;
    }

    public String getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
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

    public LinkedList<PatientBiomedicalIndicator<T>> getPatientIndicatorValues() {
        return new LinkedList<>(patientIndicatorValues);
    }

    public void setValues(LinkedList<PatientBiomedicalIndicator<T>> values) {
        this.patientIndicatorValues = values;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void add(PatientBiomedicalIndicator<T> measure) {
        if(measure != null) {
            patientIndicatorValues.add(measure);
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


    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == getClass()) {
            return this.name.equalsIgnoreCase(((BiomedicalIndicator<?>) o).name);
        }
        return false;
    }



    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
