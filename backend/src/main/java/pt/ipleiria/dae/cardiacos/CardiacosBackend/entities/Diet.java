package pt.ipleiria.dae.cardiacos.CardiacosBackend.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries({
        @NamedQuery(
                name = "getAllDiets",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class Diet extends PRC {

    public Diet() {
    }

    public Diet(int code, String name, String description) {
        super(code, name, description);
    }


}
