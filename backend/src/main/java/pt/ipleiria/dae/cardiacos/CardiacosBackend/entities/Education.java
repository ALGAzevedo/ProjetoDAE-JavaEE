package pt.ipleiria.dae.cardiacos.CardiacosBackend.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllEucations",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class Education extends PRC {

    public Education() {

    }

    public Education(int code, String name, String description) {
        super(code, name, description);
    }


}
