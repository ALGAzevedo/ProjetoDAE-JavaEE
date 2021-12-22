package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPrcs",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class PRC implements Serializable {

    @Id
    private int code;

    @NotNull
    private String name;

    @Nullable
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private HealthcareProfessional healthCareProfessional;

    @Nullable
    private Behaviour behaviourPrescription;

    @Nullable
    private Diet dietPrescription;

    @Nullable
    private Education educationPrescription;

    @Nullable
    private Exercise exercisePrescription;

    @Nullable
    private PharmacologicalTherapy pharmacologicalTherapyPrescription;

    @Nullable
    private SmokingCessation smokingCessationPrescription;

    public PRC() {
    }

    public PRC(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
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
}
