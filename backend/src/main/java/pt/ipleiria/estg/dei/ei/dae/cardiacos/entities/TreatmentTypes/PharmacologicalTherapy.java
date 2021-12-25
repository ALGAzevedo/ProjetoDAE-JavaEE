package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "PHARMACOLOGICAL_THERAPYS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllPharmacologyTherapies",
                query = "SELECT c FROM PharmacologicalTherapy c ORDER BY c.name" // JPQL
        )
})
public class PharmacologicalTherapy extends TreatmentType {


    public PharmacologicalTherapy() {
    }

    public PharmacologicalTherapy(String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional, PRC prc) {
        super(name, description, startDate, endDate, healthCareProfessional, prc);
    }
}
