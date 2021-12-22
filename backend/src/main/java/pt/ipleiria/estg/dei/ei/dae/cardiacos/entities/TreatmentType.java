package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class TreatmentType {
    @Id
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

    public TreatmentType() {
    }

    public TreatmentType(int code, String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthCareProfessional = healthCareProfessional;
    }
}
