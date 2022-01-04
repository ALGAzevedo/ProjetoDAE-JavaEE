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
    private String treatmentType;
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
    @Getter
    @Setter
    private Boolean isActive;
    @Getter
    @Setter
    private Boolean isDeleted;


    public TreatmentTypeResponseDTO() {
    }

    public TreatmentTypeResponseDTO(Integer code, String name, String treatmentType, String description, LocalDate startDate, LocalDate endDate,
                                    HealthcareProfessionalResponseDTO HealthcareProfessional, PRCResponseDTO prc, Boolean isActive, Boolean isDeleted) {
        this.code = code;
        this.treatmentType = treatmentType;
        this.name = name;
        this.description = description;
        this.startDate = startDate.toString();
        this.endDate = endDate.toString();
        this.healthcareProfessional = healthcareProfessional;
        this.prc = prc;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }
}
