package pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities;

import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.Enum.MaritalStatus;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Date;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "findAllAdministrators",
                query = "SELECT s FROM Administrator s ORDER BY s.name" // JPQL
        )
})
public class Administrator extends User{
    public Administrator() {
    }

    public Administrator(String name, String username, String email,
                         Gender gender, Date birthDate, Country country, String social_security_number,
                         String password, MaritalStatus maritalStatus, String address,
                         String city, String postal_code, String phone_number, String emergency_phone_number) {

        super(name, username, email, gender, birthDate, country, social_security_number,
                password, maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);
    }
}
