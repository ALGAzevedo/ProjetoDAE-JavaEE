package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

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
    public void preCreate(E entity) throws MyEntityNotFoundException, MyIllegalArgumentException, MyConstraintViolationException {
        HealthcareProfessional healthcareProfessional = healthcareProfissionalBean.findOrFail(entity.getHealthCareProfessional().getUsername());
        PRC prc = prcBean.findOrFail(entity.getPrc().getCode());

        if(entity.getStartDate().isBefore(prc.getStartDate())) {
            throw new MyIllegalArgumentException("startDate: Start date can not be before PRC start date.");
        }

        if(entity.getEndDate().isAfter(prc.getEndDate())){
            prc.setEndDate(entity.getEndDate());
            prcBean.update(prc);
        }

        entity.setHealthCareProfessional(healthcareProfessional);
        entity.setPrc(prc);
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
