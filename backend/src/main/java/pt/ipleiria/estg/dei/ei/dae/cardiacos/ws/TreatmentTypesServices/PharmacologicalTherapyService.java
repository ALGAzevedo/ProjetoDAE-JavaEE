package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.PharmacologicalTherapyCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.PharmacologicalTreatmentReponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.EducationBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.PharmacologicalTherapyBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Education;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.PharmacologicalTherapy;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
