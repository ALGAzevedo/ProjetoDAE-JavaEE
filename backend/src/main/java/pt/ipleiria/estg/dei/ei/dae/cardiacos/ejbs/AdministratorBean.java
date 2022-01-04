package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Stateless
//public class AdministratorBean extends UserBean<Administrator, String> {
public  class AdministratorBean extends UserBean<Administrator>
{

    public AdministratorBean() {
    }

    //PATCHES
    public Administrator patchIsSuper(String username, Boolean isAdmin) throws MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        Administrator administrator  = findOrFail(username);

        try {
            administrator.setSuperAdmin(isAdmin);
            super.update(administrator);
            return findOrFail(administrator.getUsername());
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public List<Administrator> getAdministrators(MultivaluedMap<String, String> queryParams) {
        return getUsers(queryParams);

    }

    public Administrator softDelete(Administrator administrator){
        administrator.softDelete();
        return administrator;
    }

}
