package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(
        name = "PRCS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllPRCs",
                query = "SELECT c FROM PRC c ORDER BY c.code" // JPQL
        ),
})
public class PRC extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer code;

    @NotNull
    @Getter
    @Setter
    private String name;

    @Nullable
    @Getter
    @Setter
    private String description;

    @NotNull
    @Getter
    @Setter
    private LocalDate startDate;

    @NotNull
    @Getter
    @Setter
    private LocalDate endDate;

    @OneToMany(mappedBy = "prc", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<TreatmentType> treatmentTypeList;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PATIENT_PRC")
    @Getter
    @Setter
    private Patient patient;

    @NotNull
    @Getter
    @Setter
    private Boolean isActive;

    @NotNull
    @Getter
    @Setter
    private Boolean isDeleted;


    public PRC() {
        this.isActive = true;
        this.isDeleted = false;
        this.treatmentTypeList = new LinkedList<TreatmentType>();
    }

    public PRC(String name, String description, LocalDate startDate, LocalDate endDate, Patient patient) {
        this.name = name;
        this.isActive = true;
        this.isDeleted = false;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patient = patient;
        this.treatmentTypeList = new LinkedList<TreatmentType>();
    }

    public void addTreatmentType(TreatmentType treatmentType) {
        if (!this.treatmentTypeList.contains(treatmentType)) {
            this.treatmentTypeList.add(treatmentType);
        }
    }

    public void inactivatePrc() {
        this.isActive = !this.isActive;
    }

    public Boolean softDelete() {
        var treatments = this.getTreatmentTypeList();
        var aux = 0;
        for ( TreatmentType treatment : treatments) {
            if (!treatment.getIsDeleted()){
                aux += 1;
                break;
            }
        }
        if (aux == 0) {
            this.isDeleted = true;
            return true;
        }
        return false;
    }
}
