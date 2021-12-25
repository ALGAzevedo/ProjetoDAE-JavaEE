package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.EducationBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Education;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
