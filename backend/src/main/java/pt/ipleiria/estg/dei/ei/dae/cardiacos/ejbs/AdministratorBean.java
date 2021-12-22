package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorCreateDto;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.validation.ConstraintViolationException;

@Stateless
public class AdministratorBean extends BaseBean<Administrator, String> {

    public AdministratorBean() {
        super(Administrator.class);
    }

    //CREATE
    public Administrator create(Administrator administrator) throws MyConstraintViolationException, MyEntityExistsException, MyEntityNotFoundException {
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
    public Administrator edit(Administrator administratorin) throws MyConstraintViolationException, MyEntityNotFoundException {
        DtosMapper<Administrator, Administrator> mapper = new DtosMapper<>(Administrator.class);
        Administrator administrator  = findOrFail(administratorin.getUsername());

        try {
            super.edit(mapper.getMappedEntity(administratorin));
            return findOrFail(administratorin.getUsername());
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    //DELETE
    public void remove(String username) throws MyEntityNotFoundException {
        remove(findOrFail(username));
    }


    //PATCHES
    public Administrator patchIsSuper(String username, Boolean isAdmin) throws MyConstraintViolationException, MyEntityNotFoundException {
        Administrator administrator  = findOrFail(username);

        try {
            administrator.setSuperAdmin(isAdmin);
            super.edit(administrator);
            return findOrFail(administrator.getUsername());
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }






}
