package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.EducationBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Diet;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Education;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("educations") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class EducationService extends BaseService<Education, Integer, EducationBean, EducationCreateDTO, EducationResponseDTO> {

    @EJB
    protected EducationBean educationBean;

    @Override
    protected EducationBean getEntityBean() {
        return educationBean;
    }

    @Override
    @DELETE
    @Path("{pk}")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response delete(@PathParam("pk") Integer primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        Education education = educationBean.findOrFail(primaryKey);
        educationBean.softDelete(education);
        educationBean.update(education);

        return Response.noContent().build();
    }
}
