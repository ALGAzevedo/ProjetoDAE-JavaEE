package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.AuthServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PasswordCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AuthBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class ConfirmService {

    @EJB
    private PatientBean patientBean;
    @EJB
    private AuthBean authBean;

    @POST
    @Path("/confirm")
    public Response updateUserPassword(PasswordCreateDTO passDTO) throws Exception {

        authBean.confirmToken(passDTO);

        return Response.status(Response.Status.OK).entity("Patient password updated!").build();

    }
}
