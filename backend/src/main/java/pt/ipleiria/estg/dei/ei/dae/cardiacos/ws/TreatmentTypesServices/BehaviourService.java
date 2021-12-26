package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.TreatmentTypesServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.BehaviourCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs.BehaviourResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.BehaviourBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Behaviour;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("behaviours") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class BehaviourService extends BaseService<Behaviour, Integer, BehaviourBean, BehaviourCreateDTO, BehaviourResponseDTO> {
    @EJB
    private BehaviourBean behaviourBean;
    
    @Override
    protected BehaviourBean getEntityBean() {
        return behaviourBean;
    }
}
