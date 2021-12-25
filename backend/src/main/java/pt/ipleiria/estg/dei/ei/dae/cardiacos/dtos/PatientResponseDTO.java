package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.time.LocalDate;
import java.util.Date;

public class PatientResponseDTO extends UserResponseDTO{
    public PatientResponseDTO() {
    }

    public PatientResponseDTO(String name, String username, String email, Gender gender, LocalDate birthdate, Country country, String socialSecurityNumber, MaritalStatus maritalStatus, String address, String city, String postalCode, String phoneNumber, String emergencyPhoneNumber) {
        super(name, username, email, gender, birthdate, country, socialSecurityNumber, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
    }
}
