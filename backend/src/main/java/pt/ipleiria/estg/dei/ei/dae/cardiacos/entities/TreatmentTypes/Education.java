package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(
        name = "EDUCATIONS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllEucations",
                query = "SELECT c FROM Education c ORDER BY c.name" // JPQL
        )
})
public class Education extends TreatmentType {

    public Education() {

    }

    public Education(String name, String description, LocalDate startDate, LocalDate endDate, HealthcareProfessional healthCareProfessional, PRC prc) {
        super(name, description, startDate, endDate, healthCareProfessional, prc);
    }
}
