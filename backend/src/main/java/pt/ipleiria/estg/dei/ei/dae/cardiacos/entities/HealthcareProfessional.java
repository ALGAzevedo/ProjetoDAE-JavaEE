package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

    @OneToMany(mappedBy = "healthCareProfessional", cascade = CascadeType.DETACH) //TODO: DETACH TO NOT REMOVE TREATMENTS
    private List<TreatmentType> treatments;

    public HealthcareProfessional() {
    }

    public HealthcareProfessional(String name, String username, String email,
                                  Gender gender, Date birthDate, Country country, String socialSecurityNumber,
                                  String password, MaritalStatus maritalStatus, String address, String city,
                                  String postalCode, String phoneNumber, String emergencyPhoneNumber,
                                  String institutionalEmail, String institutionalPhone) {

        super(name, username, email, gender, birthDate, country, socialSecurityNumber, password, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
        this.institutionalEmail = institutionalEmail;
        this.institutionalPhone = institutionalPhone;
    }

    public String getInstitutional_email() {
        return institutionalEmail;
    }

    public void setInstitutional_email(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public String getInstitutionalPhone() {
        return institutionalPhone;
    }

    public void setInstitutionalPhone(String institutionalPhone) {
        this.institutionalPhone = institutionalPhone;
    }
}
