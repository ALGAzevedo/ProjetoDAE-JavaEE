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
    private String institutional_email;
    private String institutional_phone;

    public HealthcareProfessional() {
    }

    public HealthcareProfessional(String name, String username, String email,
                                  Gender gender, Date birthDate, Country country, String social_security_number,
                                  String password, MaritalStatus maritalStatus, String address, String city,
                                  String postal_code, String phone_number, String emergency_phone_number,
                                  String institutional_email, String institutional_phone) {
        super(name, username, email, gender, birthDate, country, social_security_number, password, maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);
        this.institutional_email = institutional_email;
        this.institutional_phone = institutional_phone;
    }

    public String getInstitutional_email() {
        return institutional_email;
    }

    public void setInstitutional_email(String institutional_email) {
        this.institutional_email = institutional_email;
    }

    public String getInstitutional_phone() {
        return institutional_phone;
    }

    public void setInstitutional_phone(String institutional_phone) {
        this.institutional_phone = institutional_phone;
    }
}
