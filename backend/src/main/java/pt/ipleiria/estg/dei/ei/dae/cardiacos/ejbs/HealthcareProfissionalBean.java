package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

@Stateless
public class HealthcareProfissionalBean extends UserBean<HealthcareProfessional, String> {
    public HealthcareProfissionalBean() {
        super(HealthcareProfessional.class);
    }

    //CREATE
    public HealthcareProfessional create(HealthcareProfessional healthcareProfessional) throws MyConstraintViolationException, MyEntityExistsException, MyEntityNotFoundException {
        return super.create(healthcareProfessional);
    }
    //UPDATE
    public HealthcareProfessional edit(HealthcareProfessional healthcareProfessionalIn) throws MyConstraintViolationException, MyEntityNotFoundException {
        return super.edit(healthcareProfessionalIn);
    }
    //DELETE
    public void remove(String username) throws MyEntityNotFoundException {
        remove(findOrFail(username));
    }



}
