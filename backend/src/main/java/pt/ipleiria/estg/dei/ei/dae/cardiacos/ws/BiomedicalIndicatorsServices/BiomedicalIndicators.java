package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BiomedicalIndicatorsServices;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BIomedicalIdicatorUpdateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorGeneralResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalindicatorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.utils.EntityMapper;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("biomedicalindicators") // relative url web path for this service
public class BiomedicalIndicators {
    @EJB
    BiomedicalindicatorBean biomedicalindicatorBean;

    private ModelMapper mapper = new ModelMapper();

    @GET
    @Path("/")
    public Response PostQualitativeValue(){
        var listind = biomedicalindicatorBean.all();
        return Response.ok(toDTOs(listind)).build();
    }


    //In this case it is a change from qualitative to quantitative or vice versa
    @PUT
    @Path("/{id}")
    public Response PutBiomedicalIndicator(@PathParam("id") Long id, BIomedicalIdicatorUpdateDTO dto) throws MyConstraintViolationException, MyEntityNotFoundException, MyEntityExistsException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        var indicator = biomedicalindicatorBean.changeTypeOfIndicator(id, dto);

        return Response.ok(toDTO(indicator)).build();

    }

    @DELETE
    @Path("/{id}")
    public Response DeleteBiomedicalIndicator(@PathParam("id") Long id) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {

        biomedicalindicatorBean.destroy(id);
        return Response.noContent().build();

    }



    private BiomedicalIndicatorGeneralResponseDTO toDTO(BiomedicalIndicator ind) {
        return mapper.map(ind, BiomedicalIndicatorGeneralResponseDTO.class);
    }

    private List<BiomedicalIndicatorGeneralResponseDTO> toDTOs(List<BiomedicalIndicator> inds) {
        return inds.stream().map(this::toDTO).collect(Collectors.toList());
    }













}
