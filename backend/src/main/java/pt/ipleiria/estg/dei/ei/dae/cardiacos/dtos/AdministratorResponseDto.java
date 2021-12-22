package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.util.Date;

public class AdministratorResponseDto extends UserResponseDto {
    public AdministratorResponseDto(String name, String username, String email, Gender gender,
                                    Date birthDate, Country country, String social_security_number,
                                    MaritalStatus maritalStatus, String address, String city, String postal_code,
                                    String phone_number, String emergency_phone_number) {

        super(name, username, email, gender, birthDate, country, social_security_number,
                maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);
    }

    public AdministratorResponseDto() {
        super();
    }
}
