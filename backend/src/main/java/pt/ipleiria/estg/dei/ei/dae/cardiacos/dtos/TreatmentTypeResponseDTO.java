package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.util.Date;

public class TreatmentTypeResponseDTO implements DTO{

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

    public TreatmentTypeResponseDTO() {
    }

    public TreatmentTypeResponseDTO(String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional, PRC prc) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthCareProfessional = healthCareProfessional;
        this.prc = prc;
    }
}
