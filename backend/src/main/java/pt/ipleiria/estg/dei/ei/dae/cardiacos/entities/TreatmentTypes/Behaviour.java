package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(
        name = "BEHAVIOURS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllBehaviours",
                query = "SELECT c FROM Behaviour c ORDER BY c.name" // JPQL
        )
})
public class Behaviour extends TreatmentType {

    public Behaviour() {
    }

    public Behaviour(String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional) {
        super(name, description, startDate, endDate, healthCareProfessional);
    }
}