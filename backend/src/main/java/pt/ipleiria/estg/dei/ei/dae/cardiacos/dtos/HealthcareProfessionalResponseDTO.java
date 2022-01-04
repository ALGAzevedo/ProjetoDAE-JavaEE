package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.time.LocalDate;
import java.util.Date;

public class HealthcareProfessionalResponseDTO extends UserResponseDTO {
    private String institutionalEmail;
    private String institutionalPhone;

    public HealthcareProfessionalResponseDTO() {
    }

    public HealthcareProfessionalResponseDTO(String name, String username, String email, Gender gender,
                                             LocalDate birthdate, Country country, String socialSecurityNumber,
                                             MaritalStatus maritalStatus, String address, String city,
                                             String postalCode, String phoneNumber,
                                             String emergencyPhoneNumber, String institutionalEmail,
                                             String institutionalPhone, Date isDeleted) {

        super(name, username, email, gender, birthdate, country, socialSecurityNumber, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber, isDeleted);
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
