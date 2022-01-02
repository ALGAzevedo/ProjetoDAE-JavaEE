package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.Email.EmailBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserBean<E extends User> extends BaseBean<E, String>{
    @EJB
    AuthBean authBean;

    @EJB
    private EmailBean emailBean;

    public UserBean() {
    }

    @Override
    public void preCreate(User entity) throws MyUniqueConstraintViolationException, MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        if(find(entity.getUsername()) != null) {
            throw new MyEntityExistsException("User with username: " + entity.getUsername() + " already exists");
        }
        if (!findWithEmail(entity.getEmail()).isEmpty()) {
            throw new MyUniqueConstraintViolationException("Email: " + entity.getEmail() + " already registred");
        }

    }
    @Override
    public void postCreate(User entity) throws MyConstraintViolationException, MyEntityNotFoundException, MyEntityExistsException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        String token = authBean.generateToken();
        authBean.create(new Auth(entity.getUsername(),token));
        String confirmationLink = "http://localhost:8081/confirm?token=" + token;
        emailBean.send(entity.getEmail(), "Confirm your email",entity.getName(), confirmationLink);
    }

    public List findWithEmail(String email) {
        Query query = em.createNamedQuery("getWithEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }


}
