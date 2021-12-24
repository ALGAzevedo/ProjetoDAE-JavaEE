package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AdministratorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("administrators") // relative url web path for this service
public class AdministratorService extends BaseService<Administrator, String, AdministratorBean, AdministratorCreateDTO, AdministratorResponseDTO> {
    @EJB
    private AdministratorBean administratorBean;

    @Override
    protected AdministratorBean getEntityBean() {
        return administratorBean;
    }

    @PATCH
    @Path("{username}/super")
    public Response PatchAdministratorSuperPrivileges(@PathParam("username") String username, boolean isAdmin) throws MyEntityNotFoundException, MyConstraintViolationException {
        Administrator admin =  administratorBean.patchIsSuper(username, isAdmin);
        return Response.ok(admin).build();

    }



}
