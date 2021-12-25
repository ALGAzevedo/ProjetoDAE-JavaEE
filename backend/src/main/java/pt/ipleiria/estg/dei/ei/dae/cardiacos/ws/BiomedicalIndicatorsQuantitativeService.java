package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicatorQualitativeDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicatorQuantitativeDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQualitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQuantitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("BiomedicalIndicatorsQuantitative") // relative url web path for this service
public class BiomedicalIndicatorsQuantitativeService extends BaseService<BiomedicalIndicatorsQuantitative, Long,
        BiomedicalIndicatorsQuantitativeBean, BiomedicalIndicatorQuantitativeDTO, BiomedicalIndicatorQualitativeDTO> {

    @EJB
    BiomedicalIndicatorsQuantitativeBean biomedicalIndicatorsQuantitativeBean;

    @Override
    protected BiomedicalIndicatorsQuantitativeBean getEntityBean() {
        return biomedicalIndicatorsQuantitativeBean;
    }
}
