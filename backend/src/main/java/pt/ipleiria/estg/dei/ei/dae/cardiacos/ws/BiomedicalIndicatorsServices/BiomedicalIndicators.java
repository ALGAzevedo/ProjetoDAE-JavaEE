package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.BiomedicalIndicatorsServices;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQualitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalindicatorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyUniqueConstraintViolationException;

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







}
