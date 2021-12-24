package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllHealthcareProfessionals",
                query = "SELECT s FROM HealthcareProfessional s ORDER BY s.name" // JPQL
        )
})
public class HealthcareProfessional extends User{
    private String institutionalEmail;
    private String institutionalPhone;

    public HealthcareProfessional() {
    }

    public HealthcareProfessional(String name, String username, String email,
                                  Gender gender, LocalDate birthDate, Country country, String socialSecurityNumber,
                                  String password, MaritalStatus maritalStatus, String address, String city,
                                  String postalCode, String phoneNumber, String emergencyPhoneNumberumber,
                                  String institutionalEmail, String institutionalPhone) {
        super(name, username, email, gender, birthDate, country, socialSecurityNumber, password, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumberumber);
        this.institutionalEmail = institutionalEmail;
        this.institutionalPhone = institutionalPhone;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public String getInstitutionalPhone() {
        return institutionalPhone;
    }

    public void setInstitutionalPhone(String institutionalPhone) {
        this.institutionalPhone = institutionalPhone;
    }
}
