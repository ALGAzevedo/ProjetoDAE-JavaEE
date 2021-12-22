package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;


public class AdministratorCreateDto extends UserCreateDto{

    public AdministratorCreateDto(String name, String username, String email, Gender gender,
                                  int birthDateYear, int birthDateMonth, int birthDateDay, Country country, String social_security_number, String password,
                                  MaritalStatus maritalStatus, String address, String city,
                                  String postal_code, String phone_number, String emergency_phone_number) {

        super(name, username, email, gender, birthDateYear, birthDateMonth, birthDateDay, country, social_security_number, password,
                maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);

    }

    public AdministratorCreateDto() {

    }
}
