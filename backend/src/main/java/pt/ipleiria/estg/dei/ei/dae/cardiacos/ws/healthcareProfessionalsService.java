package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.HealthcareProfissionalBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("healthcareprofissionals") // relative url web path for this service
public class healthcareProfessionalsService extends BaseService<HealthcareProfessional, String,
        HealthcareProfissionalBean, HealthcareProfessionalCreateDTO, HealthcareProfessionalResponseDTO> {
    @EJB
    private HealthcareProfissionalBean healthcareProfissionalBean;

    @Override
    protected HealthcareProfissionalBean getEntityBean() {
        return healthcareProfissionalBean;
    }


    @GET
    @Path("")
    public Response all(@Context UriInfo ui ) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        List<HealthcareProfessional> pi = healthcareProfissionalBean.getHealthcareProfessionals(queryParams);
        var dtos = mapper.serialize(pi, getDtoResponseClass());
        return Response.ok(dtos).build();
    }

}


