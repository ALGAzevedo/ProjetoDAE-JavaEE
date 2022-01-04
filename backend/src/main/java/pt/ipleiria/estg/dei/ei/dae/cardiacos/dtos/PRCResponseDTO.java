package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PRCResponseDTO implements DTO{

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
    private String patientUsername;
    @Getter
    @Setter
    private String isActive;
    @Getter
    @Setter
    private String isDeleted;


    public PRCResponseDTO() {
    }

    public PRCResponseDTO(Integer code, String name, String description, LocalDate startDate, LocalDate endDate, String patientUsername, Boolean isActive, Boolean isDeleted) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.startDate = startDate.toString();
        this.endDate = endDate.toString();
        this.patientUsername = patientUsername;
        this.isActive = isActive.toString();
        this.isDeleted = isDeleted.toString();
    }


}
