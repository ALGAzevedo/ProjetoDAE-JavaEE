package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.EducationResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.ExerciseCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.ExerciseResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.EducationBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.ExerciseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Education;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Exercise;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("exercises") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class ExerciseService extends BaseService<Exercise, Integer, ExerciseBean, ExerciseCreateDTO, ExerciseResponseDTO> {

    @EJB
    private ExerciseBean exerciseBean;

    @Override
    protected ExerciseBean getEntityBean() {
        return exerciseBean;
    }
}
