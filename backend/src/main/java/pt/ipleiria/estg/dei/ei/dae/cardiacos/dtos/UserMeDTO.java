package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.time.LocalDate;
import java.util.Date;

public class UserMeDTO extends HealthcareProfessionalResponseDTO{
    private String userType;

    public UserMeDTO() {
    }

    public UserMeDTO(String name, String username, String email, Gender gender, LocalDate birthdate, Country country,
                     String socialSecurityNumber, MaritalStatus maritalStatus, String address, String city,
                     String postalCode, String phoneNumber, String emergencyPhoneNumber, String institutionalEmail,
                     String institutionalPhone, String userType, Date isDeleted) {
        super(name, username, email, gender, birthdate, country, socialSecurityNumber, maritalStatus, address, city,
                postalCode, phoneNumber, emergencyPhoneNumber, institutionalEmail, institutionalPhone, isDeleted);
        this.userType = userType;

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
