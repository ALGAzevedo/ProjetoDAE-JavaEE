package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllHealthcareProfessionals",
                query = "SELECT s FROM HealthcareProfessional s ORDER BY s.name" // JPQL
        )
})
public class HealthcareProfessional extends User{
    @Column(unique=true)
    @Email
    private String institutionalEmail;
    @Column(unique=true)
    @Pattern(regexp="^[9][0-9]{8}$",
            message="{invalid.institutionalPhone}")
    private String institutionalPhone;

    @OneToMany(mappedBy = "healthCareProfessional", cascade = CascadeType.DETACH) //TODO: DETACH TO NOT REMOVE TREATMENTS
    private List<TreatmentType> treatmentTypeList;

    public HealthcareProfessional() {
        this.treatmentTypeList = new LinkedList<TreatmentType>();
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

    public List<TreatmentType> getTreatmentTypeList() {
        return treatmentTypeList;
    }

    public void setTreatmentTypeList(List<TreatmentType> treatmentTypeList) {
        this.treatmentTypeList = treatmentTypeList;
    }

    public void addTreatment(TreatmentType treatmentType) {
        if(!this.treatmentTypeList.contains(treatmentType)) {
            this.treatmentTypeList.add(treatmentType);
        }
    }
}
