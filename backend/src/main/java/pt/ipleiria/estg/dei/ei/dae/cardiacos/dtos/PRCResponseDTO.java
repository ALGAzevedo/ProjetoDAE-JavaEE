package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import java.util.Date;
import java.util.List;

public class PRCResponseDTO implements DTO{

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String patientUsername;
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

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public List<TreatmentType> getTreatmentTypeList() {
        return treatmentTypeList;
    }

    public void setTreatmentTypeList(List<TreatmentType> treatmentTypeList) {
        this.treatmentTypeList = treatmentTypeList;
    }
}
