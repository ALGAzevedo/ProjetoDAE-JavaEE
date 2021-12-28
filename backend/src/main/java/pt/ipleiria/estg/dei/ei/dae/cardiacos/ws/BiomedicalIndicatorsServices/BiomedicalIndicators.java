package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BiomedicalIndicatorsServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BIomedicalIdicatorUpdateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalindicatorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("biomedicalindicators") // relative url web path for this service
public class BiomedicalIndicators {
    @EJB
    BiomedicalindicatorBean biomedicalindicatorBean;

    @GET
    @Path("/")
    public Response PostQualitativeValue(){
        return Response.ok(biomedicalindicatorBean.getAll()).build();

    }


    //In this case it is a change from qualitative to quantitative or vice versa
    @PUT
    @Path("/{id}")
    public Response PutBiomedicalIndicator(@PathParam("id") Long id, BIomedicalIdicatorUpdateDTO dto) throws MyConstraintViolationException, MyEntityNotFoundException, MyEntityExistsException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        return Response.ok(biomedicalindicatorBean.changeTypeOfIndicator(id, dto)).build();

    }









}
