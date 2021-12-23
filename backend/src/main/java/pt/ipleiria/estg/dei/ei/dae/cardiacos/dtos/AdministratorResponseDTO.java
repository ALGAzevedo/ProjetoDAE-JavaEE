package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import java.util.Date;

public class AdministratorResponseDTO extends UserResponseDTO {
    public boolean isSuperAdmin;
    public AdministratorResponseDTO(String name, String username, String email, Gender gender,
                                    Date birthDate, Country country, String socialSecurityNumber,
                                    MaritalStatus maritalStatus, String address, String city, String postalCode,
                                    String phoneNumber, String emergencyPhoneNumber, boolean isSuper) {

        super(name, username, email, gender, birthDate, country, socialSecurityNumber,
                maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
        this.isSuperAdmin= isSuper;
    }

    public AdministratorResponseDTO() {
        super();
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void isSuperAdmin(boolean aSuper) {
        isSuperAdmin = aSuper;
    }
}
