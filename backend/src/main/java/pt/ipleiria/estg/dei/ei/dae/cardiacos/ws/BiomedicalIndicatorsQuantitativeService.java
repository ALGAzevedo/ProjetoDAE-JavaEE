package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicatorQualitativeDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicatorQuantitativeDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQualitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("BiomedicalQualitativeIndicators") // relative url web path for this service
public class BiomedicalIndicatorsQuantitativeService extends BaseService<BiomedicalIndicatorsQualitative, Long, BiomedicalIndicatorsQualitativeBean,
        BiomedicalIndicatorQualitativeDTO, BiomedicalIndicatorQuantitativeDTO> {
    @EJB
    BiomedicalIndicatorsQualitativeBean biomedicalIndicatorsQualitativeBean;

    @Override
    protected BiomedicalIndicatorsQualitativeBean getEntityBean() {
        return biomedicalIndicatorsQualitativeBean;
    }

    @POST
    @Path("{id}/values")
    public Response PostQualitativeValue(@PathParam("id") Long id,
                                                      BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException {

        BiomedicalIndicatorsQualitative indicator =  biomedicalIndicatorsQualitativeBean.addNewBiomedicalIndicatorsQualitativePossibleValue(id, dto);
        return Response.ok(indicator).build();

    }
    @DELETE
    @Path("{id}/values")
    public Response DeleteQualitativeValue(@PathParam("id") Long id,
                                         BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException {

        BiomedicalIndicatorsQualitative indicator =  biomedicalIndicatorsQualitativeBean.removeBiomedicalIndicatorsQualitativePossibleValue(id, dto);
        return Response.ok(indicator).build();

    }


}
