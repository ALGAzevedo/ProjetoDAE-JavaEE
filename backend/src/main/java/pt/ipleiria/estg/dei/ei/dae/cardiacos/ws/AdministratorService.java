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

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


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
    public Response PatchAdministratorSuperPrivileges(@PathParam("username") String username, boolean isAdmin) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        Administrator admin =  administratorBean.patchIsSuper(username, isAdmin);
        return Response.ok(admin).build();

    }

    @GET
    @Path("")
    public Response all(@Context UriInfo ui ) {
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
