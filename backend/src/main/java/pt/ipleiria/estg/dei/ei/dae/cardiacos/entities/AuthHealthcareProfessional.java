package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.Entity;

@Entity
public class AuthHealthcareProfessional extends Auth{
    public AuthHealthcareProfessional() {
    }

    public AuthHealthcareProfessional(String username, String token) {
        super(username, token);
    }
}
