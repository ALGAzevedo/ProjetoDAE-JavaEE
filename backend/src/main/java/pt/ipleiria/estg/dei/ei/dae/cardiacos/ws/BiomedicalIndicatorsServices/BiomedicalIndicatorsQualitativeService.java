package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BiomedicalIndicatorsServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorQualitativeCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorQualitativeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQualitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyUniqueConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BaseService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("biomedicalindicators/qualitative") // relative url web path for this service
@RolesAllowed({"AuthAdministrator", "AuthHealthcareProfessional"})
public class BiomedicalIndicatorsQualitativeService extends BaseService<BiomedicalIndicatorsQualitative, Long, BiomedicalIndicatorsQualitativeBean,
        BiomedicalIndicatorQualitativeCreateDTO, BiomedicalIndicatorQualitativeResponseDTO> {
    @EJB
    BiomedicalIndicatorsQualitativeBean biomedicalIndicatorsQualitativeBean;

    @Override
    protected void preUpdate(BiomedicalIndicatorsQualitative entity) {
        entity.setPossibleValues(new HashSet<>());
    }

    @Override
    protected BiomedicalIndicatorsQualitativeBean getEntityBean() {
        return biomedicalIndicatorsQualitativeBean;
    }

    @POST
    @Path("{id}/values")
    @RolesAllowed({"AuthAdministrator"})
    public Response PostQualitativeValue(@PathParam("id") Long id,
                                                      BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException {

        BiomedicalIndicatorsQualitative indicator =  biomedicalIndicatorsQualitativeBean.addNewBiomedicalIndicatorsQualitativePossibleValue(id, dto);
        return Response.ok(indicator).build();

    }
    @DELETE
    @Path("{id}/values")
    @RolesAllowed({"AuthAdministrator"})
    public Response DeleteQualitativeValue(@PathParam("id") Long id,
                                         BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException {

        BiomedicalIndicatorsQualitative indicator =  biomedicalIndicatorsQualitativeBean.removeBiomedicalIndicatorsQualitativePossibleValue(id, dto);
        return Response.ok(indicator).build();

    }


}
