package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.SmokingCessationCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.SmokingCessationResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.EducationBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.SmokingCessationBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Education;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.SmokingCessation;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("smokingCessations") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class SmokingCessationService extends BaseService<SmokingCessation, Integer, SmokingCessationBean, SmokingCessationCreateDTO, SmokingCessationResponseDTO> {

    @EJB
    private SmokingCessationBean smokingCessationBean;

    @Override
    protected SmokingCessationBean getEntityBean() {
        return smokingCessationBean;
    }

    @Override
    @DELETE
    @Path("{pk}")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response delete(@PathParam("pk") Integer primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        SmokingCessation smokingCessation = smokingCessationBean.findOrFail(primaryKey);
        smokingCessationBean.softDelete(smokingCessation);
        smokingCessationBean.update(smokingCessation);

        return Response.noContent().build();
    }
}
