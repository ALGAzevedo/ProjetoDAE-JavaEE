package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

public class HealthcareProfessionalCreateDTO extends UserCreateDTO {
    private String institutionalEmail;
    private String institutionalPhone;

    public HealthcareProfessionalCreateDTO() {
    }

    public HealthcareProfessionalCreateDTO(String institutionalEmail, String institutionalPphone) {
        this.institutionalEmail = institutionalEmail;
        this.institutionalPhone = institutionalPphone;
    }

    public HealthcareProfessionalCreateDTO(String name, String username, String email, Gender gender, int birthDateYear, int birthDateMonth, int birthDateDay, Country country, String socialSecurityNumber, String password, MaritalStatus maritalStatus, String address, String city, String postalCode, String phoneNumber, String emergencyPhoneNumber, String institutionalEmail, String institutionalPhone) {
        super(name, username, email, gender, birthDateYear, birthDateMonth, birthDateDay, country, socialSecurityNumber, password, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
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
