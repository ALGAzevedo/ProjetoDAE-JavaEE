package pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.dtos;

import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.Enum.MaritalStatus;

import java.util.Date;

public class AdministratorCreateDto extends UserCreateDto{

    public AdministratorCreateDto(String name, String username, String email, Gender gender,
                                  Date birthDate, Country country, String social_security_number, String password,
                                  MaritalStatus maritalStatus, String address, String city,
                                  String postal_code, String phone_number, String emergency_phone_number) {

        super(name, username, email, gender, birthDate, country, social_security_number, password,
                maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);
    }
}
