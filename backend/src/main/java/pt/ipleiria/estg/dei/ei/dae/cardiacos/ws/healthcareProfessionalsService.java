package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.HealthcareProfissionalBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;


import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;


@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("healthcareprofissionals") // relative url web path for this service
@RolesAllowed({"AuthHealthcareProfessional","AuthAdministrator"})
public class healthcareProfessionalsService extends BaseService<HealthcareProfessional, String,
        HealthcareProfissionalBean, HealthcareProfessionalCreateDTO, HealthcareProfessionalResponseDTO> {
    @EJB
    private HealthcareProfissionalBean healthcareProfissionalBean;
    @EJB
    private PatientBean patientBean;

    @Override
    protected HealthcareProfissionalBean getEntityBean() {
        return healthcareProfissionalBean;
    }


    @GET
    @Path("")
    @RolesAllowed({"AuthHealthcareProfessional","AuthAdministrator"})
    public Response all(@Context UriInfo ui ) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        List<HealthcareProfessional> pi = healthcareProfissionalBean.getHealthcareProfessionals(queryParams);
        var dtos = mapper.serialize(pi, getDtoResponseClass());
        return Response.ok(dtos).build();
    }
    @PATCH
    @Path("{username}/patients/{patientusername}")
    @RolesAllowed({"AuthHealthcareProfessional","AuthAdministrator"})
    public Response PatchAddPatient(@PathParam("username") String username, @PathParam("patientusername") String patientUsername) throws MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        HealthcareProfessional healthcareProfessional = healthcareProfissionalBean.addPatient(username, patientUsername);
        return Response.ok(healthcareProfessional).build();
    }

    @PATCH
    @Path("{username}/patients/{patientusername}")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response PatchRemovePatient(@PathParam("username") String username, @PathParam("patientusername") String patientUsername) throws MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        HealthcareProfessional healthcareProfessional = healthcareProfissionalBean.removePatient(username, patientUsername);
        return Response.ok(healthcareProfessional).build();
    }

    @GET
    @Path("{username}/patients")
    @RolesAllowed({"AuthHealthcareProfessional"})
    public Response GetPatients(@PathParam("username") String username) throws MyEntityNotFoundException {
        HealthcareProfessional healthcareProfessional = healthcareProfissionalBean.findOrFail(username);

        var professionalPatientList = healthcareProfessional.getPatientList();

        return Response.ok(patientBean.patientsToDTOs(professionalPatientList)).build();
    }

    @Override
    @DELETE
    @Path("{pk}")
    @RolesAllowed({"AuthAdministrator"})
    public Response delete(@PathParam("pk") String primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        HealthcareProfessional healthcareProfessional = healthcareProfissionalBean.findOrFail(primaryKey);
        healthcareProfissionalBean.softDelete(healthcareProfessional);
        healthcareProfissionalBean.update(healthcareProfessional);

        return Response.noContent().build();
    }

}


