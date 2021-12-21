package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

@Stateless
public class AdministratorBean extends BaseBean<Administrator, String> {

    public AdministratorBean() {
        super(Administrator.class);
    }

    //CREATE
    public Administrator create(Administrator administrator) throws MyConstraintViolationException, MyEntityExistsException {
        if(find(administrator.getUsername()) != null) {
            throw new MyEntityExistsException("Administrator with username: " + administrator.getUsername() + "already exists");
        }

        try {
            super.create(administrator);
            return findOrFail(administrator.getUsername());

        }catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
    //UPDATE

    //DELETE




}
