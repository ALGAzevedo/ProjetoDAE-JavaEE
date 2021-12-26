package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PRCCreateDTO implements DTO{

    @Getter
    @Setter
    private String name;
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
    private String patientUsername;
    @Getter
    @Setter
    private List<TreatmentType> treatmentTypeList;

    public PRCCreateDTO() {
//        this.treatmentTypeList = new LinkedList<TreatmentTypeDTO>();
    }

    public PRCCreateDTO(String name, String description, String startDate, String endDate, String patientUsername) {
        this.name = name;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.patientUsername = patientUsername;
//        this.treatmentTypeList = new LinkedList<TreatmentTypeDTO>();
    }

    public PRCCreateDTO(String name, String description, String startDate, String endDate, String patientUsername, List<TreatmentType> treatmentTypeList) {
        this.name = name;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.patientUsername = patientUsername;
        this.treatmentTypeList = treatmentTypeList;
    }
}
