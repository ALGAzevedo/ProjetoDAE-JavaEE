package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.AuthServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/auth/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class RegistrationService extends BaseService<Patient, String, PatientBean, PatientCreateDTO, PatientResponseDTO> {
    private static final Logger log =
            Logger.getLogger(RegistrationService.class.getName());

    @EJB
    private PatientBean patientBean;

    @Override
    protected PatientBean getEntityBean() {
        return patientBean;
    }

}
