package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(
        name = "TREATMENT_TYPES"
)
@NamedQueries({
        @NamedQuery(
                name = "getAllTreatmentTypes",
                query = "SELECT c FROM TreatmentType c ORDER BY c.name" // JPQL
        )
})
public class TreatmentType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer code;

    @NotNull
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String treatmentType;

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

    @ManyToOne
    @JoinColumn(name = "PROFESSIONAL_TREATMENT")
    @NotNull
    @Getter
    @Setter
    private HealthcareProfessional healthCareProfessional;

    @ManyToOne
    @JoinColumn(name = "PRC_TREATMENT")
    @NotNull
    @Getter
    @Setter
    private PRC prc;

    @NotNull
    @Getter
    @Setter
    private Boolean isActive;

    @NotNull
    @Getter
    @Setter
    private Boolean isDeleted;

    public TreatmentType() {
        this.isActive = true;
        this.isDeleted = false;
    }

    public TreatmentType(String name, String treatmentType, String description, LocalDate startDate, LocalDate endDate, HealthcareProfessional healthCareProfessional, PRC prc) {
        this.name = name;
        this.treatmentType = treatmentType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthCareProfessional = healthCareProfessional;
        this.prc = prc;
        this.isActive = true;
        this.isDeleted = false;
    }

}
