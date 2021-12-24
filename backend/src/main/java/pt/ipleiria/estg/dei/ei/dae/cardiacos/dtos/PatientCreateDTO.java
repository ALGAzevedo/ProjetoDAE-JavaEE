package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

public class PatientCreateDTO extends UserCreateDTO{

    public PatientCreateDTO() {
    }

    public PatientCreateDTO(String name, String username, String email, Gender gender, int birthDateYear, int birthDateMonth, int birthDateDay, Country country, String socialSecurityNumber, String password, MaritalStatus maritalStatus, String address, String city, String postalCode, String phoneNumber, String emergencyPhoneNumber) {
        super(name, username, email, gender, birthDateYear, birthDateMonth, birthDateDay, country, socialSecurityNumber, password, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
    }
}