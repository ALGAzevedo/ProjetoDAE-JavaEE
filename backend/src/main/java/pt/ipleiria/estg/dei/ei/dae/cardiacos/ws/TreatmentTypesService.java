package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentTypeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Behaviour;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("treatmentTypes") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class TreatmentTypesService extends BaseService<TreatmentType, Integer, TreatmentTypeBean, TreatmentTypeCreateDTO, TreatmentTypeResponseDTO>{

    @EJB
    private TreatmentTypeBean treatmentTypeBean;

    @Override
    protected TreatmentTypeBean getEntityBean() {
        return treatmentTypeBean;
    }
}
