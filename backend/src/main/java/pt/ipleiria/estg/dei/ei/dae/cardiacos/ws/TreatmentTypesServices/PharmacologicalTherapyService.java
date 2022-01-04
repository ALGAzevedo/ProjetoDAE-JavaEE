package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.PharmacologicalTherapyCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.PharmacologicalTreatmentReponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.EducationBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.PharmacologicalTherapyBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Education;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.PharmacologicalTherapy;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pharmacologicalTherapys") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class PharmacologicalTherapyService extends BaseService<PharmacologicalTherapy, Integer, PharmacologicalTherapyBean, PharmacologicalTherapyCreateDTO, PharmacologicalTreatmentReponseDTO> {

    @EJB
    private PharmacologicalTherapyBean pharmacologicalTherapyBean;

    @Override
    protected PharmacologicalTherapyBean getEntityBean() {
        return pharmacologicalTherapyBean;
    }

    @Override
    @DELETE
    @Path("{pk}")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response delete(@PathParam("pk") Integer primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        PharmacologicalTherapy pharmacologicalTherapy = pharmacologicalTherapyBean.findOrFail(primaryKey);
        pharmacologicalTherapyBean.softDelete(pharmacologicalTherapy);
        pharmacologicalTherapyBean.update(pharmacologicalTherapy);

        return Response.noContent().build();
    }
}
