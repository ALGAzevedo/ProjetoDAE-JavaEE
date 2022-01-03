package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import org.apache.commons.lang3.StringUtils;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.Email.EmailBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MultivaluedMap;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserBean<E extends User> extends BaseBean<E, String>{
    @EJB
    AuthBean authBean;

    @EJB
    private EmailBean emailBean;

    public UserBean() {
    }

    @Override
    public void preCreate(User entity) throws MyUniqueConstraintViolationException, MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        if(find(entity.getUsername()) != null) {
            throw new MyEntityExistsException("User with username: " + entity.getUsername() + " already exists");
        }
        if (!findWithEmail(entity.getEmail()).isEmpty()) {
            throw new MyUniqueConstraintViolationException("email: " + entity.getEmail() + " already registred");
        }

    }
    @Override
    public void postCreate(User entity) throws MyConstraintViolationException, MyEntityNotFoundException, MyEntityExistsException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        String token = authBean.generateToken();
        authBean.addUser(new Auth(entity.getUsername(),token), entity);
        String confirmationLink = "http://localhost:8081/confirm?token=" + token;
        emailBean.send(entity.getEmail(), "Confirm your email",entity.getName(), confirmationLink);
    }

    public List findWithEmail(String email) {
        Query query = em.createNamedQuery("getWithEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }


    public List<E> getUsers(MultivaluedMap<String, String> queryParams) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        StringBuilder queryBuilder = new StringBuilder();
        List<String> whereCause = new ArrayList<String>();
        queryBuilder.append("SELECT p FROM " + getEntityClass().getSimpleName()+ " p");

        whereCause.add("p.isDeleted IS NULL");

        if(queryParams.containsKey("patientUsername")) {
            whereCause.add("UPPER(p.username) =  UPPER(:patientUsername)");
            paramaterMap.put("patientUsername", queryParams.get("patientUsername").get(0));
        }
        if(queryParams.containsKey("patientName")) {
            whereCause.add("UPPER(p.name) LIKE  UPPER(:patientName)");
            paramaterMap.put("patientName", "%"+queryParams.get("patientName").get(0)+"%");
        }

        if(queryParams.containsKey("email")) {
            whereCause.add("UPPER(p.email) LIKE  UPPER(:email)");
            paramaterMap.put("email", "%"+queryParams.get("email").get(0)+"%");
        }
        if(queryParams.containsKey("socialSecurityNumber")) {
            whereCause.add("UPPER(p.socialSecurityNumber) LIKE  UPPER(:socialSecurityNumber)");
            paramaterMap.put("socialSecurityNumber", "%"+queryParams.get("socialSecurityNumber").get(0)+"%");
        }
        if(queryParams.containsKey("phoneNumber")) {
            whereCause.add("UPPER(p.phoneNumber) LIKE  UPPER(:phoneNumber)");
            paramaterMap.put("phoneNumber", "%"+queryParams.get("phoneNumber").get(0)+"%");
        }

        //INSTITUTIONAL EMAIL AND PHONE NUMBER CAN BE USED TO SEARCH IF USER IS HP
        if(getEntityClass().getSimpleName().equals("HealthcareProfessional")) {
            if(queryParams.containsKey("institutionalEmail")) {
                whereCause.add("UPPER(p.institutionalEmail) LIKE  UPPER(:institutionalEmail)");
                paramaterMap.put("institutionalEmail", "%"+queryParams.get("institutionalEmail").get(0)+"%");
            }
            if(queryParams.containsKey("institutionalPhone")) {
                whereCause.add("UPPER(p.institutionalPhone) LIKE  UPPER(:institutionalPhone)");
                paramaterMap.put("institutionalPhone", "%"+queryParams.get("institutionalPhone").get(0)+"%");
            }

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
