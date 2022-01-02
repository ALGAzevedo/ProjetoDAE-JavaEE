package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.Entity;

@Entity
public class AuthPatient extends Auth{
    public AuthPatient() {
    }

    public AuthPatient(String username, String token) {
        super(username, token);
    }
}
