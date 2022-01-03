package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import org.apache.commons.lang3.StringUtils;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.core.MultivaluedMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class TreatmentTypeBean<E extends TreatmentType, PK extends Integer> extends BaseBean<E, Integer> {

    @EJB
    private PRCBean prcBean;

    @EJB
    private HealthcareProfissionalBean healthcareProfessionalBean;

    public TreatmentTypeBean() {
    }

    @Override
    public void preCreate(E entity) throws MyEntityNotFoundException, MyIllegalArgumentException, MyConstraintViolationException {
        HealthcareProfessional healthcareProfessional = healthcareProfessionalBean.findOrFail(entity.getHealthCareProfessional().getUsername());
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
    public void postCreate(E entity) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException { //TODO: TO VERIFY WITH TEACHER
        PRC prc = prcBean.findOrFail(entity.getPrc().getCode());
        HealthcareProfessional healthcareProfessional = healthcareProfessionalBean.findOrFail(entity.getHealthCareProfessional().getUsername());

        prc.addTreatmentType(entity);
        prcBean.update(prc);

        healthcareProfessional.addTreatment(entity);
        healthcareProfessional.addPatient(entity.getPrc().getPatient());
        healthcareProfessionalBean.update(healthcareProfessional);

        entity.getPrc().getPatient().addHealthcareProfessional(healthcareProfessional);
        //TODO: ESTÁ CERTO ?

        this.myAwesomeMethod();
    }

    //TODO: TESTE PARA CURRENT USER
    @Resource
    private SessionContext context;

    public void myAwesomeMethod() {
        String currentUser = context.getCallerPrincipal().getName();
        System.out.println(currentUser);
    }

    protected List<E> getTreatments(MultivaluedMap<String, String> queryParams) throws MyEntityNotFoundException {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        StringBuilder queryBuilder = new StringBuilder();
        List<String> whereCause = new ArrayList<String>();
        queryBuilder.append("SELECT p FROM " + getEntityClass().getSimpleName()+ " p");

        whereCause.add("p.isDeleted = FALSE");

        if(queryParams.containsKey("treatmentName")) {
            whereCause.add("UPPER(p.name) LIKE UPPER(:treatmentName)");
            paramaterMap.put("prcName", "%"+queryParams.get("prcName").get(0)+"%");
        }
        //TODO: NOT WORKING
        if(queryParams.containsKey("healthcareProfessionalUsername")) {
            whereCause.add("p.healthcareProfessional = :healthcareProfessional");
            paramaterMap.put("healthcareProfessional", healthcareProfessionalBean.findOrFail(queryParams.get("healthcareProfessionalUsername").get(0)));
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
}
