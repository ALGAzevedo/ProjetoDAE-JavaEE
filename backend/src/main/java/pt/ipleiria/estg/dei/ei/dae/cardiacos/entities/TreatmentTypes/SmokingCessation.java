package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
        @NamedQuery(
                name = "getAllSmokingCessations",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class SmokingCessation extends TreatmentType {

    public SmokingCessation() {
    }
    public SmokingCessation(int code, String name, String description) {
        super(code, name, description);
    }
}
