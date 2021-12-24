package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PRCCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PRCResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PRCBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BaseEntity;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("prcs") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class PRCService extends BaseService<PRC, Integer, PRCBean, PRCCreateDTO, PRCResponseDTO> {

    @EJB
    protected PRCBean prcBean;
    
    @Override
    protected PRCBean getEntityBean() {
        return prcBean;
    }
}
