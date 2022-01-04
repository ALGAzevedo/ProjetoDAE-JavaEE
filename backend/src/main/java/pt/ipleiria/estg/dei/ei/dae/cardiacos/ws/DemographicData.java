package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.DemographicDataBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("demographicdata") // relative url web path for this service
@RolesAllowed({"AuthAdministrator","AuthPatient", "AuthHealthcareProfessional"})
public class DemographicData {
    @EJB
    private DemographicDataBean demographicDataBean;

    @GET
    @Path("")
    public Response getAll() {

        return Response.ok(DemographicDataBean.getAll()).build();

    }

    @GET
    @Path("/countries")
    public Response getAllCountries() {

        return Response.ok(DemographicDataBean.getCountries()).build();

    }
    @GET
    @Path("/maritalStatus")
    public Response getAllMaritalStatus() {

        return Response.ok(DemographicDataBean.getMartitalStatus()).build();

    }

    @GET
    @Path("/genders")
    public Response getAllGenders() {

        return Response.ok(DemographicDataBean.getGenders()).build();

    }


}
