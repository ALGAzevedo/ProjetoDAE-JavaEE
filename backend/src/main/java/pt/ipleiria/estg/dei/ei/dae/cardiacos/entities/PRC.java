package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(
        name = "PRCS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllPrcs",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class PRC extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer code;

    @NotNull
    private String name;

    @Nullable
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @OneToMany(mappedBy = "prc", cascade = CascadeType.REMOVE)
    private List<TreatmentType> treatmentTypeList;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PATIENT_PRC")
    private Patient patient;


    public PRC() {
        this.treatmentTypeList = new LinkedList<TreatmentType>();
    }

    public PRC(String name, String description, Date startDate, Date endDate, Patient patient) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patient = patient;
        this.treatmentTypeList = new LinkedList<TreatmentType>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<TreatmentType> getTreatmentTypeList() {
        return treatmentTypeList;
    }

    public void setTreatmentTypeList(List<TreatmentType> treatmentTypeList) {
        this.treatmentTypeList = treatmentTypeList;
    }

    public void addTreatment(TreatmentType treatmentType) {
        if(!this.treatmentTypeList.contains(treatmentType)) {
            this.treatmentTypeList.add(treatmentType);
        }
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
