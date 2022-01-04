package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.BehaviourCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.BehaviourResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.DietCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.DietResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.BehaviourBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.DietBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Behaviour;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Diet;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("diets") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class DietService extends BaseService<Diet, Integer, DietBean, DietCreateDTO, DietResponseDTO> {
    @EJB
    private DietBean dietBean;

    @Override
    protected DietBean getEntityBean() {
        return dietBean;
    }

    @Override
    @DELETE
    @Path("{pk}")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response delete(@PathParam("pk") Integer primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        Diet diet = dietBean.findOrFail(primaryKey);
        dietBean.softDelete(diet);
        dietBean.update(diet);

        return Response.noContent().build();
    }
}
