package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllAdministrators",
                query = "SELECT s FROM Administrator s WHERE s.isDeleted IS NULL ORDER BY s.name" // JPQL
        )
})
public class Administrator extends User {
    private boolean isSuperAdmin;

    public Administrator() {
        isSuperAdmin = false;
    }

    public Administrator(String name, String username, String email,
                         Gender gender, LocalDate birthDate, Country country, String socialSecurityNumber, MaritalStatus maritalStatus, String address,
                         String city, String postalCode, String phoneNumber, String emergencyPhoneNumber) {

        super(name, username, email, gender, birthDate, country, socialSecurityNumber, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
        isSuperAdmin = false;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

}
