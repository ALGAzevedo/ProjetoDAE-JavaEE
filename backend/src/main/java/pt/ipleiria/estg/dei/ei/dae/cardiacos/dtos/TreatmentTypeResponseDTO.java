package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.time.LocalDate;
import java.util.Date;

public class TreatmentTypeResponseDTO implements DTO{

    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String startDate;
    @Getter
    @Setter
    private String endDate;
    @Getter
    @Setter
    private HealthcareProfessionalResponseDTO healthcareProfessional;
    @Getter
    @Setter
    private PRCResponseDTO prc;

    public TreatmentTypeResponseDTO() {
    }

    public TreatmentTypeResponseDTO(Integer code, String name, String description, LocalDate startDate, LocalDate endDate, HealthcareProfessionalResponseDTO healthcareProfessional, PRCResponseDTO prc) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.startDate = startDate.toString();
        this.endDate = endDate.toString();
        this.healthcareProfessional = healthcareProfessional;
        this.prc = prc;
    }
}
