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

    public HealthcareProfessionalCreateDTO(String name, String username, String email, Gender gender, int birthDateYear, int birthDateMonth, int birthDateDay, Country country, String social_security_number, String password, MaritalStatus maritalStatus, String address, String city, String postal_code, String phone_number, String emergency_phone_number, String institutional_email, String institutional_phone) {
        super(name, username, email, gender, birthDateYear, birthDateMonth, birthDateDay, country, social_security_number, password, maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);
        this.institutionalEmail = institutional_email;
        this.institutionalPhone = institutional_phone;
    }

    public String getInstitutional_email() {
        return institutionalEmail;
    }

    public void setInstitutional_email(String institutional_email) {
        this.institutionalEmail = institutional_email;
    }

    public String getInstitutional_phone() {
        return institutionalPhone;
    }

    public void setInstitutional_phone(String institutional_phone) {
        this.institutionalPhone = institutional_phone;
    }
}
