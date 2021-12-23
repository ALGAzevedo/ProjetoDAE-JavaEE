package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AdministratorCreateDTO extends UserCreateDTO {

    public AdministratorCreateDTO(String name, String username, String email, Gender gender,
                                  int birthDateYear, int birthDateMonth, int birthDateDay, Country country, String social_security_number, String password,
                                  MaritalStatus maritalStatus, String address, String city,
                                  String postal_code, String phone_number, String emergency_phone_number) {

        super(name, username, email, gender, new GregorianCalendar(birthDateYear, birthDateMonth, birthDateDay).getTime(),
                country, social_security_number, password,
                maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);

    }

    public AdministratorCreateDTO() {

    }



}
