package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class TreatmentTypeCreateDTO implements DTO{

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
    private LocalDate startDate;
    @Getter
    @Setter
    private LocalDate endDate;
    @Getter
    @Setter
    private String healthcareProfessionalUsername;
    @Getter
    @Setter
    private String prcCode;

    public TreatmentTypeCreateDTO() {
    }

    public TreatmentTypeCreateDTO(String name, String treatmentType, String description, LocalDate startDate, LocalDate endDate, String healthcareProfessionalUsername, String prcCode) {
        this.name = name;
        this.treatmentType = treatmentType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthcareProfessionalUsername = healthcareProfessionalUsername;
        this.prcCode = prcCode;
    }
}
