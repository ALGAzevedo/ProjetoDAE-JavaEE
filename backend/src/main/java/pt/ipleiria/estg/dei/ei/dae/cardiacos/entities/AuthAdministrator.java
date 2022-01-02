package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.Entity;

@Entity
public class AuthAdministrator extends Auth{
    public AuthAdministrator() {
    }

    public AuthAdministrator(String username, String token) {
        super(username, token);
    }
}
