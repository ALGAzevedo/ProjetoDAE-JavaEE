package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "getAllPharmacologyTherapies",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class PharmacologicalTherapy extends TreatmentType {


    public PharmacologicalTherapy() {
    }

    public PharmacologicalTherapy(int code, String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional) {
        super(code, name, description, startDate, endDate, healthCareProfessional);
    }
}
