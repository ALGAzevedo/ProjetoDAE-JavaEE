package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PRCCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PRCResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PRCBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

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

    @GET
    @Path("{code}/treatmentTypes")
    public Response GetAllTreatmentTypes(@PathParam("code") Integer code) throws MyEntityNotFoundException {
        PRC prc = prcBean.findOrFail(code);

        var prcTreatmentTypes = prc.getTreatmentTypeList();

        return Response.ok(treatmentTypesToDTOs(prcTreatmentTypes)).build();
    }

    private List<TreatmentTypeResponseDTO> treatmentTypesToDTOs(List<TreatmentType> treatmentTypeList) {
        return treatmentTypeList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private TreatmentTypeResponseDTO toDTO(TreatmentType treatmentType) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(treatmentType, TreatmentTypeResponseDTO.class);
    }

    @PATCH
    @Path("{code}/inactive")
    public Response PatchPrcInactivate(@PathParam("code") Integer code) throws MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        PRC prc =  prcBean.patchInactivatePrc(code);
        return Response.ok(prc).build();
    }
}
