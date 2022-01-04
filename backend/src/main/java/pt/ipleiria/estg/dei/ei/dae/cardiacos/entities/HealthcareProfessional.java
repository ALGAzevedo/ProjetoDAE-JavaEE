package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.Nullable;
import lombok.Getter;
import lombok.Setter;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllHealthcareProfessionals",
                query = "SELECT s FROM HealthcareProfessional s WHERE s.isDeleted IS NULL ORDER BY s.name" // JPQL
        )
})
public class HealthcareProfessional extends User{
    @Column(unique=true)
    @Email
    @NotNull
    private String institutionalEmail;
    @Column(unique=true)
    @Pattern(regexp="^[9][0-9]{8}$",
            message="Phone Number invalid")
    @NotNull
    private String institutionalPhone;

    @Nullable
    @Getter
    @Setter
    @OneToMany(mappedBy = "healthCareProfessional", cascade = CascadeType.DETACH)
    private List<TreatmentType> treatmentTypeList;

    @Nullable
    @Getter
    @Setter
    @ManyToMany(mappedBy = "healthcareProfessionalList")
    private List<Patient> patientList;

    public HealthcareProfessional() {
        this.treatmentTypeList = new LinkedList<TreatmentType>();
        this.patientList = new LinkedList<Patient>();
    }

    public HealthcareProfessional(String name, String username, String email,
                                  Gender gender, LocalDate birthDate, Country country, String socialSecurityNumber,MaritalStatus maritalStatus, String address, String city,
                                  String postalCode, String phoneNumber, String emergencyPhoneNumberumber,
                                  String institutionalEmail, String institutionalPhone) {
        super(name, username, email, gender, birthDate, country, socialSecurityNumber,maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumberumber);
        this.institutionalEmail = institutionalEmail;
        this.institutionalPhone = institutionalPhone;
        this.treatmentTypeList = new LinkedList<TreatmentType>();
        this.patientList = new LinkedList<Patient>();
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

    public void addTreatment(TreatmentType treatmentType) {
        if(!this.treatmentTypeList.contains(treatmentType)) {
            this.treatmentTypeList.add(treatmentType);
        }
    }

    public void addPatient(Patient patient) {
        if(!this.patientList.contains(patient)) {
            this.patientList.add(patient);
        }
    }

    public void removePatient(Patient patient){
        this.patientList.remove(patient);
    }
}
