package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AdministratorCreateDTO extends UserCreateDTO {

    public AdministratorCreateDTO(String name, String username, String email, Gender gender,
                                  LocalDate birthdate, Country country, String socialSecurityNumber, String password,
                                  MaritalStatus maritalStatus, String address, String city,
                                  String postalCode, String phoneNumber, String emergencyPhoneNumber) {

        super(name, username, email, gender, birthdate,
                country, socialSecurityNumber, password,
                maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);

    }

    public AdministratorCreateDTO() {

    }



}
