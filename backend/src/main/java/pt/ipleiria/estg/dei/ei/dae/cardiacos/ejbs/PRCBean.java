package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import org.apache.commons.lang3.StringUtils;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MultivaluedMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    @Override
    public void postCreate(PRC entity) throws MyEntityNotFoundException, MyConstraintViolationException { //TODO: TO VERIFY
        entity.getPatient().addPrc(entity);
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

    public List<PRC> getPrcs(MultivaluedMap<String, String> queryParams) throws MyEntityNotFoundException {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        StringBuilder queryBuilder = new StringBuilder();
        List<String> whereCause = new ArrayList<String>();
        queryBuilder.append("SELECT p FROM PRC p");

        whereCause.add("p.isDeleted = FALSE");

        if(queryParams.containsKey("prcName")) {
            whereCause.add("UPPER(p.name) LIKE UPPER(:prcName)");
            paramaterMap.put("prcName", "%"+queryParams.get("prcName").get(0)+"%");
        }
        if(queryParams.containsKey("patientUsername")) {
            whereCause.add("p.patient = :patient");
            paramaterMap.put("patient", patientBean.findOrFail(queryParams.get("patientUsername").get(0)));
        }

        if(queryParams.containsKey("startDate")) {
            whereCause.add("p.startDate >= :startDate");
            paramaterMap.put("startDate", LocalDate.parse(queryParams.get("startDate").get(0)));
        }
        if(queryParams.containsKey("endDate")) {
            whereCause.add("p.endDate <= :endDate");
            paramaterMap.put("endDate", LocalDate.parse(queryParams.get("endDate").get(0)));
        }

        if(!queryParams.isEmpty())
            queryBuilder.append(" where " + StringUtils.join(whereCause, " and "));

        Query jpaQuery = em.createQuery(queryBuilder.toString());


        for(String key :paramaterMap.keySet()) {
            jpaQuery.setParameter(key, paramaterMap.get(key));
        }

        return jpaQuery.getResultList();
    }

    public PRC softDelete(PRC prc){
        prc.softDelete();
        return prc;
    }
}
