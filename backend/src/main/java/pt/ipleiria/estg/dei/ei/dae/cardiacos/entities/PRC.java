package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import lombok.Getter;
import lombok.Setter;

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
                name = "getAllPrcs",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class PRC extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "prc", cascade = CascadeType.REMOVE)
    @Getter
    @Setter
    private List<TreatmentType> treatmentTypeList;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PATIENT_PRC")
    @Getter
    @Setter
    private Patient patient;


    public PRC() {
        this.treatmentTypeList = new LinkedList<TreatmentType>();
    }

    public PRC(String name, String description, LocalDate startDate, LocalDate endDate, Patient patient) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patient = patient;
        this.treatmentTypeList = new LinkedList<TreatmentType>();
    }
}
