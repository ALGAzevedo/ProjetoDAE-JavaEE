package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PatientCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PatientResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("patients") // relative url web path for this service
public class PatientService extends BaseService<Patient, String, PatientBean, PatientCreateDTO, PatientResponseDTO> {
    @EJB
    private PatientBean patientBean;

    @Override
    protected PatientBean getEntityBean() {
        return patientBean;
    }
}
