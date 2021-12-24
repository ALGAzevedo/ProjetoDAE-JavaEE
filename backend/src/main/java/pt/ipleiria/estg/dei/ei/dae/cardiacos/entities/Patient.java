package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPatients",
                query = "SELECT s FROM Patient s ORDER BY s.name" // JPQL
        )
})
public class Patient extends User{

        public Patient() {
        }

        public Patient(String name, String username, String email, Gender gender, LocalDate birthDate, Country country, String socialSecurityNumber, String password, MaritalStatus maritalStatus, String address, String city, String postalCode, String phoneNumber, String emergencyPhoneNumber) {
                super(name, username, email, gender, birthDate, country, socialSecurityNumber, password, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
        }
}
