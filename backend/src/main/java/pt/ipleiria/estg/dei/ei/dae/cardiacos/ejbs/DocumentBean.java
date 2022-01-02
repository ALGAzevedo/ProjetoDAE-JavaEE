package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Document;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class DocumentBean extends BaseBean<Document,Long> {
    @EJB
    private PatientBean patientBean;

    @Override
    public void preCreate(Document document) throws MyEntityNotFoundException {
        patientBean.findOrFail(document.getPatient().getUsername());
    }


    public Document findDocument(Long id) throws MyEntityNotFoundException {
        return findOrFail(id);
    }

    public List<Document> getPatientsDocuments(String username){
        return all();
    }

    public List<Document> getAllPatientDocuments(String username) {
        return em.createNamedQuery("getPatientDocuments").setParameter("username", username).getResultList();
    }


}
