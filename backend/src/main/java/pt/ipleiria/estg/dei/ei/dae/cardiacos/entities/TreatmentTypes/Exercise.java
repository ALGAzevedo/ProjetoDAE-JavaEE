package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
        @NamedQuery(
                name = "getAllExercises",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class Exercise extends TreatmentType {

    public Exercise() {
    }

    public Exercise(int code, String name, String description) {
        super(code, name, description);
    }


}
