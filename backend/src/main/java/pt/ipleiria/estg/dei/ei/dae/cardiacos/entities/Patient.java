package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "findAllPatients",
                query = "SELECT s FROM Patient s ORDER BY s.name" // JPQL
        )
})
public class Patient extends User{

}
