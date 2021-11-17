package pt.ipleiria.dae.cardiacos.CardiacosBackend.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllExercises",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class Exercise extends PRC {

    public Exercise() {
    }

    public Exercise(int code, String name, String description) {
        super(code, name, description);
    }


}
