package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(
        name = "ADMINISTRATORS"
)
@NamedQueries({
        @NamedQuery(
                name = "getAllAdministrators",
                query = "SELECT s FROM Administrator s ORDER BY s.name" // JPQL
        )
})
public class Administrator extends User{
    private boolean isSuperAdmin;

    public Administrator() {
        isSuperAdmin = false;
    }

    public Administrator(String name, String username, String email,
                         Gender gender, Date birthDate, Country country, String socialSecurityNumber,
                         String password, MaritalStatus maritalStatus, String address,
                         String city, String postalCode, String phoneNumber, String emergencyPhoneNumber) {

        super(name, username, email, gender, birthDate, country, socialSecurityNumber,
                password, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
        isSuperAdmin = false;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }
}
