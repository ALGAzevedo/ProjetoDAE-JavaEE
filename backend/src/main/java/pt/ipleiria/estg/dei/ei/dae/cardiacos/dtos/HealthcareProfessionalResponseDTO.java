package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.util.Date;

public class HealthcareProfessionalResponseDTO extends UserResponseDTO {
    private String institutionalEmail;
    private String institutionalPhone;

    public HealthcareProfessionalResponseDTO() {
    }

    public HealthcareProfessionalResponseDTO(String name, String username, String email, Gender gender,
                                             Date birthDate, Country country, String socialSecurityNumber,
                                             MaritalStatus maritalStatus, String address, String city,
                                             String postalCode, String phoneNumber,
                                             String emergencyPhoneNumber, String institutionalEmail,
                                             String institutionalPhone) {

        super(name, username, email, gender, birthDate, country, socialSecurityNumber, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
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
