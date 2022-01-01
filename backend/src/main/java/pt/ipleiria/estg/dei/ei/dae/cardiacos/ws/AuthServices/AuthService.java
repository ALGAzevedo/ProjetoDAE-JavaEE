package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.AuthServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AuthDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AuthBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class AuthService {
    @EJB
    private AuthBean authBean;

    @GET
    @Path("/")
    public Response auth() {
        return Response.status(Response.Status.BAD_REQUEST)
                .build();
    }

}
