package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

@Stateless
public class PRCBean extends BaseBean<PRC, Integer> {

    @EJB
    PatientBean patientBean;

    public PRCBean() {
    }

    @Override
    public void preCreate(PRC entity) throws MyEntityNotFoundException {
        Patient patient = patientBean.findOrFail(entity.getPatient().getUsername());
        entity.setPatient(patient);
        //patientBean.update(patient);

    }

    @Override
    public void postCreate(PRC entity) throws MyEntityNotFoundException, MyConstraintViolationException { //TODO: TO VERIFY
        entity.getPatient().addPrc(entity);
//        patientBean.update(patient);
    }

    //PATCHES
    public PRC patchInactivatePrc(Integer code) throws MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        PRC prc  = findOrFail(code);

        try {
            prc.inactivatePrc();
            super.update(prc);
            return findOrFail(prc.getCode());
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        } catch (MyEntityNotFoundException e) {
            throw new MyEntityNotFoundException(e.getMessage());
        } catch (MyIllegalArgumentException e) {
            throw new MyIllegalArgumentException(e.getMessage());
        }
    }

}
