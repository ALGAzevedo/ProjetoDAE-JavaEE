package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(
        name = "SMOKING_CESSATIONS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllSmokingCessations",
                query = "SELECT c FROM SmokingCessation c ORDER BY c.name" // JPQL
        )
})
public class SmokingCessation extends TreatmentType {

    public SmokingCessation() {
    }

    public SmokingCessation(String name, String treatmentType, String description, LocalDate startDate, LocalDate endDate, HealthcareProfessional healthCareProfessional, PRC prc) {
        super(name, treatmentType, description, startDate, endDate, healthCareProfessional, prc);
    }
}
