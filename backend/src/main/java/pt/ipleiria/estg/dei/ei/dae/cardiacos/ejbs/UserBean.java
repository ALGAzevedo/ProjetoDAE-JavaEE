package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyUniqueConstraintViolationException;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class UserBean<E extends User, PK extends String> extends BaseBean<E, String>{
    public UserBean() {
    }

    @Override
    public void preCreate(User entity) throws MyUniqueConstraintViolationException, MyEntityExistsException {
        if(find(entity.getUsername()) != null) {
            throw new MyEntityExistsException("User with username: " + entity.getUsername() + " already exists");
        }
        if (!findWithEmail(entity.getEmail()).isEmpty()) {
            throw new MyUniqueConstraintViolationException("Email: " + entity.getEmail() + " already registred");
        }
    }

    public List findWithEmail(String email) {
        return em.createQuery(
                        "SELECT c FROM User c WHERE c.email = :custEmail")
                .setParameter("custEmail", email)
                .getResultList();
    }
}
