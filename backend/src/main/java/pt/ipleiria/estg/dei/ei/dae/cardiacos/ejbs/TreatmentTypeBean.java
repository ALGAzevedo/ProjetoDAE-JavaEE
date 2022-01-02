package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TreatmentTypeBean<E extends TreatmentType, PK extends Integer> extends BaseBean<E, Integer> {

    @EJB
    private PRCBean prcBean;

    @EJB
    private HealthcareProfissionalBean healthcareProfissionalBean;

    public TreatmentTypeBean() {
    }

    @Override
    public void preCreate(E entity) throws MyEntityNotFoundException {
        System.out.println(entity.getPrc().getCode());
        System.out.println(entity.getHealthCareProfessional());
        HealthcareProfessional healthcareProfessional = healthcareProfissionalBean.findOrFail(entity.getHealthCareProfessional().getUsername());
        entity.setHealthCareProfessional(healthcareProfessional);

        PRC prc = prcBean.findOrFail(entity.getPrc().getCode());
        entity.setPrc(prc);
        //patientBean.update(patient);
    }

    @Override
    public void postCreate(E entity) throws MyEntityNotFoundException, MyConstraintViolationException { //TODO: TO VERIFY WITH TEACHER

        PRC prc = prcBean.findOrFail(entity.getPrc().getCode());
        HealthcareProfessional healthcareProfissional = healthcareProfissionalBean.findOrFail(entity.getHealthCareProfessional().getUsername());

        prc.addTreatmentType(entity);
        prcBean.update(prc);

        healthcareProfissional.addTreatment(entity);
        healthcareProfissionalBean.update(healthcareProfissional);

        System.out.println(entity.getPrc());
        System.out.println(entity.getHealthCareProfessional());

//        entity.setHealthCareProfessional(healthcareProfissional);
//        entity.setPrc(prc);

    }
}
