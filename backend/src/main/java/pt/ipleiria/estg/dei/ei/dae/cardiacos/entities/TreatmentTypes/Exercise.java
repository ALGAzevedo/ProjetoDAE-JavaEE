package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "EXERCISES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllExercises",
                query = "SELECT c FROM Exercise c ORDER BY c.name" // JPQL
        )
})
public class Exercise extends TreatmentType {

    public Exercise() {
    }

    public Exercise(String name, String description, Date startDate, Date endDate, HealthcareProfessional healthCareProfessional) {
        super(name, description, startDate, endDate, healthCareProfessional);
    }
}