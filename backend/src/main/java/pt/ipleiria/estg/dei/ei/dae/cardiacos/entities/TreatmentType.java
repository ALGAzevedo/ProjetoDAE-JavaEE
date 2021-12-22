package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
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
public class TreatmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", unique = true, nullable = false, insertable = false,updatable = false) //TODO: IS THIS CORRECT?
    private int code;

    @NotNull
    private String name;

    @Nullable
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "PROFESSIONAL_TREATMENT")
    @NotNull
    private HealthcareProfessional healthCareProfessional;

    @ManyToOne
    @JoinColumn(name = "PRC_TREATMENT")
    @NotNull
    private PRC prc;

    public TreatmentType() {
    }

    public TreatmentType(String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthCareProfessional = healthCareProfessional;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public HealthcareProfessional getHealthCareProfessional() {
        return healthCareProfessional;
    }

    public void setHealthCareProfessional(HealthcareProfessional healthCareProfessional) {
        this.healthCareProfessional = healthCareProfessional;
    }

    public PRC getPrc() {
        return prc;
    }

    public void setPrc(PRC prc) {
        this.prc = prc;
    }
}
