package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;


import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;


@Stateless
public class HealthcareProfissionalBean extends UserBean<HealthcareProfessional> {
    public HealthcareProfissionalBean() {

    }

    @Override
    public void preCreate(HealthcareProfessional entity) throws MyEntityExistsException, MyUniqueConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException, MyConstraintViolationException {
        super.preCreate(entity);

        //verify institutional email
        if(!findWithInstitutionalEmail(entity.getInstitutionalEmail()).isEmpty())
            throw new MyEntityExistsException("There is already an HealthcareProfessional with "
                    + entity.getInstitutionalEmail() + " institutional email");

        //verify institutional phone
        if(!findWithInstitutionalPhone(entity.getInstitutionalPhone()).isEmpty())
            throw new MyEntityExistsException("There is already an HealthcareProfessional with "
                    + entity.getInstitutionalPhone() + " institutional phone");
    }

    public List findWithInstitutionalEmail(String email) {
        return em.createQuery(
                        "SELECT c FROM HealthcareProfessional c WHERE c.institutionalEmail = :custEmail")
                .setParameter("custEmail", email)
                .getResultList();
    }
    public List findWithInstitutionalPhone(String phone) {
        return em.createQuery(
                        "SELECT c FROM HealthcareProfessional c WHERE c.institutionalPhone = :custPhone")
                .setParameter("custPhone", phone)
                .getResultList();
    }

    public List<HealthcareProfessional> getHealthcareProfessionals(MultivaluedMap<String, String> queryParams) {
        return getUsers(queryParams);

    }


}
