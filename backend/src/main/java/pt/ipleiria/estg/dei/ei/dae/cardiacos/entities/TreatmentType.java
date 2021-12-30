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
        name = "TREATMENT_TYPES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllTreatmentTypes",
                query = "SELECT c FROM TreatmentType c ORDER BY c.name" // JPQL
        )
})
public class TreatmentType extends BaseEntity{
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

    public TreatmentType() {
    }

    public TreatmentType(String name, String description, LocalDate startDate, LocalDate endDate, HealthcareProfessional healthCareProfessional, PRC prc) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthCareProfessional = healthCareProfessional;
        this.prc = prc;
    }

}
