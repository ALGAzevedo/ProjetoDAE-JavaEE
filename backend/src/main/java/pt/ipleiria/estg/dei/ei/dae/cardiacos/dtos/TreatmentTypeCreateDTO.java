package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class TreatmentTypeCreateDTO implements DTO{

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Date startDate;
    @Getter
    @Setter
    private Date endDate;
    @Getter
    @Setter
    private HealthcareProfessional healthCareProfessional;
    @Getter
    @Setter
    private PRC prc;

    public TreatmentTypeCreateDTO() {
    }

    public TreatmentTypeCreateDTO(String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional, PRC prc) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthCareProfessional = healthCareProfessional;
        this.prc = prc;
    }
}
