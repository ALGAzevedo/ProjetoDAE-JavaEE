package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.BehaviourCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.BehaviourResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.BehaviourBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Behaviour;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("behaviours") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@RolesAllowed({"AuthAdministrator", "AuthHealthcareProfessional", "AuthPatient"})
public class BehaviourService extends BaseService<Behaviour, Integer, BehaviourBean, BehaviourCreateDTO, BehaviourResponseDTO> {
    @EJB
    private BehaviourBean behaviourBean;
    
    @Override
    protected BehaviourBean getEntityBean() {
        return behaviourBean;
    }

    @GET
    @Path("treatmentTypeValues")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response GetAllTreatmentTypeValues() throws MyEntityNotFoundException {

        String possibleValues[] = {"behaviours", "diets", "educations", "exercises", "pharmacologicalTherapys", "smokingCessation" };

        return Response.ok(possibleValues).build();
    }

    @GET
    @Path("")
    @RolesAllowed({"AuthHealthcareProfessional", "AuthPatient"})
    public Response all(@Context UriInfo ui ) throws MyEntityNotFoundException {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        List<Behaviour> behaviours = behaviourBean.getBehaviours(queryParams);
        var dtos = mapper.serialize(behaviours, getDtoResponseClass());
        return Response.ok(dtos).build();
    }

    @Override
    @DELETE
    @Path("{pk}")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response delete(@PathParam("pk") Integer primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        Behaviour behaviour = behaviourBean.findOrFail(primaryKey);
        behaviourBean.softDelete(behaviour);
        behaviourBean.update(behaviour);

        return Response.noContent().build();
    }
}
