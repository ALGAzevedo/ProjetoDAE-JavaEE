package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import java.util.Date;
import java.util.List;

public class PRCResponseDTO implements DTO{

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
    private String patientUsername;
    @Getter
    @Setter
    private List<TreatmentType> treatmentTypeList;

    public PRCResponseDTO() {
//        this.treatmentTypeList = new LinkedList<TreatmentTypeDTO>();
    }

    public PRCResponseDTO(String name, String description, Date startDate, Date endDate, String patientUsername) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patientUsername = patientUsername;
//        this.treatmentTypeList = new LinkedList<TreatmentTypeDTO>();
    }

    public PRCResponseDTO(String name, String description, Date startDate, Date endDate, String patientUsername, List<TreatmentType> treatmentTypeList) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patientUsername = patientUsername;
        this.treatmentTypeList = treatmentTypeList;
    }

}
