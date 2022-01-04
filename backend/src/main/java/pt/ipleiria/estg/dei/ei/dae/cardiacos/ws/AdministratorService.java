package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AdministratorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Principal;
import java.util.List;


@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("administrators") // relative url web path for this service
public class AdministratorService extends BaseService<Administrator, String, AdministratorBean, AdministratorCreateDTO, AdministratorResponseDTO> {
    @EJB
    private AdministratorBean administratorBean;

    @Context
    private SecurityContext securityContext;

    @Override
    protected AdministratorBean getEntityBean() {
        return administratorBean;
    }

    @PATCH
    @Path("{username}/super")
    public Response PatchAdministratorSuperPrivileges(@PathParam("username") String username) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        //ONLY SUPERADMIN CAN MAKE ANOTHER SUPER
        Principal principal = securityContext.getUserPrincipal();
        if(!securityContext.isUserInRole("AuthAdministrator")) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        if(!administratorBean.findOrFail(principal.getName()).isSuperAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Administrator admin =  administratorBean.patchIsSuper(username);
        return Response.ok(admin).build();

    }

    @GET
    @Path("")
    @RolesAllowed({"AuthAdministrator"})
    public Response getAll(@Context UriInfo ui ) {


        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        List<Administrator> pi = administratorBean.getAdministrators(queryParams);
        var dtos = mapper.serialize(pi, getDtoResponseClass());
        return Response.ok(dtos).build();
    }

    @Override
    @DELETE
    @Path("{pk}")
    public Response delete(@PathParam("pk") String primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        Administrator administrator = administratorBean.findOrFail(primaryKey);
        administratorBean.softDelete(administrator);
        administratorBean.update(administrator);

        return Response.noContent().build();
    }

}
