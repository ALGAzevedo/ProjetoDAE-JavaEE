package pt.ipleiria.dae.cardiacos.CardiacosBackend.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
        @NamedQuery(
                name = "getAllPharmacologyTherapies",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class PharmacologicalTherapy extends PRC {


    public PharmacologicalTherapy() {
    }

    public PharmacologicalTherapy(int code, String name, String description) {
        super(code, name, description);
    }

}
