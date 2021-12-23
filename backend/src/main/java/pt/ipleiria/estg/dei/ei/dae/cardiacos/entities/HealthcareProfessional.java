package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Date;


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
                                  Gender gender, Date birthDate, Country country, String social_security_number,
                                  String password, MaritalStatus maritalStatus, String address, String city,
                                  String postal_code, String phone_number, String emergency_phone_number,
                                  String institutional_email, String institutional_phone) {
        super(name, username, email, gender, birthDate, country, social_security_number, password, maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);
        this.institutionalEmail = institutional_email;
        this.institutionalPhone = institutional_phone;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutional_email) {
        this.institutionalEmail = institutional_email;
    }

    public String getInstitutionalPhone() {
        return institutionalPhone;
    }

    public void setInstitutionalPhone(String institutional_phone) {
        this.institutionalPhone = institutional_phone;
    }
}
