package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PRCBean extends BaseBean<PRC, Integer> {

    @EJB
    PatientBean patientBean;

    public PRCBean() {
    }

    @Override
    public void postCreate(PRC entity) throws MyEntityNotFoundException, MyConstraintViolationException { //TODO: TO VERIFY
        Patient patient = patientBean.findOrFail(entity.getPatient().getUsername());
        if (patient == null){
            throw new MyEntityNotFoundException("Patient was not found.");
        }
        patient.addPrc(entity);
        patientBean.update(patient);
    }

}
