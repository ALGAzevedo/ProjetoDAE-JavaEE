package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorMeasureResponsePatientDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PatientBiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyUniqueConstraintViolationException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("patients") // relative url web path for this service
@RolesAllowed({"AuthAdministrator","AuthPatient", "AuthHealthcareProfessional"})
public class PatientService extends BaseService<Patient, String, PatientBean, PatientCreateDTO, PatientResponseDTO> {
    @EJB
    private PatientBean patientBean;

    @Override
    protected PatientBean getEntityBean() {
        return patientBean;
    }

    @GET
    @Path("")
    @RolesAllowed({"AuthAdministrator", "AuthHealthcareProfessional"})
    public Response all(@Context UriInfo ui ) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        List<Patient> pi = patientBean.getPatients(queryParams);
        var dtos = mapper.serialize(pi, getDtoResponseClass());
        return Response.ok(dtos).build();
    }

    //add indicators to its register
    @POST
    @Path("{username}/biomedicalRegisters/qualitative")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response PostQualitativeMeasure(@PathParam("username") String username, QualitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        patientBean.addQualitativeBiomedicalIndicator(username, dto);
        return Response.ok(dto).build();
    }

    @POST
    @Path("{username}/biomedicalRegisters/quantitative")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response PostQuantitativeMeasure(@PathParam("username") String username, QuantitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {
        System.out.println(dto.getDate());
        patientBean.addQuantitativeBiomedicalIndicator(username, dto);
        return Response.ok(dto).build();
    }

    @GET
    @Path("{username}/prcs")
    @RolesAllowed({"AuthHealthcareProfessional", "AuthPatient"})
    public Response GetAllPatientPrcs(@PathParam("username") String username) throws MyEntityNotFoundException {
        Patient patient = patientBean.findOrFail(username);

        var patientPrcsList = patient.getPrcList();

        return Response.ok(prcsToDTOs(patientPrcsList)).build();
    }
    private List<PRCResponseDTO> prcsToDTOs(List<PRC> prcsList) {
        return prcsList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private PRCResponseDTO toDTO(PRC prc) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(prc, PRCResponseDTO.class);
    }

    @GET
    @Path("{username}/biomedicalRegisters")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response getBiomedicalRegisters(@PathParam("username") String username, @Context UriInfo ui ) throws MyEntityNotFoundException {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        List<PatientBiomedicalIndicator> pi = patientBean.getPatientRegisters(username, queryParams);

        List<BiomedicalIndicatorMeasureResponsePatientDTO> list = toDTOs(pi);
        while (list.remove(null));
        return Response.ok(list).build();
        

    }

    @GET
    @Path("/biomedicalRegisters")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response GetAllBiomedicalRegisters(@Context UriInfo ui) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        List<PatientBiomedicalIndicator> pi = patientBean.filterListIndicators(queryParams, "");


        List<BiomedicalIndicatorMeasureResponsePatientDTO> list = toDTOs(pi);
        while (list.remove(null));
        return Response.ok(list).build();



    }

    @GET
    @Path("{username}/biomedicalRegisters/{id}")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response getBiomedicalRegister(@PathParam("username") String username, @PathParam("id") Long id ) throws MyEntityNotFoundException {

        return Response.ok(toDTO(patientBean.getPatientRegister(username, id))).build();


    }

    @DELETE
    @Path("{username}/biomedicalRegisters/{id}")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response PostQuantitativeMeasure(@PathParam("username") String username, @PathParam("id") Long id ) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        patientBean.removePatientRegisters(username, id);
        return Response.noContent().build();

    }
    @PUT
    @Path("{username}/biomedicalRegisters/{id}")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response PutQuantitativeMeasure(@PathParam("username") String username, @PathParam("id") Long id, QuantitativeBiomedicalIndicatorMeasureDTO dto  ) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        PatientBiomedicalIndicator ind = patientBean.editPatientRegistersQuantitative(username, id, dto);
        return Response.ok(toDTO(ind)).build();

    }
    @PUT
    @Path("{username}/biomedicalRegisters/{id}")
    @RolesAllowed({"AuthPatient", "AuthHealthcareProfessional"})
    public Response PutQualitativeMeasureQuantitative(@PathParam("username") String username, @PathParam("id") Long id, QualitativeBiomedicalIndicatorMeasureDTO dto  ) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        PatientBiomedicalIndicator ind = patientBean.editPatientRegistersQuanlitative(username, id, dto);
        return Response.ok(toDTO(ind)).build();
    }

    @Override
    @DELETE
    @Path("{pk}")
    @RolesAllowed({"AuthAdministrator", "AuthHealthcareProfessional"})
    public Response delete(@PathParam("pk") String primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        Patient patient = patientBean.findOrFail(primaryKey);
        patientBean.softDelete(patient);
        patientBean.update(patient);

        return Response.noContent().build();
    }


    private BiomedicalIndicatorMeasureResponsePatientDTO toDTO(PatientBiomedicalIndicator p) {
        //TODO random nulls are in database
        if(p == null || p.getValue() == null)
            return null;

        return new BiomedicalIndicatorMeasureResponsePatientDTO(p.getId(), p.getDate(),
                p.getValue(), p.getIndicator().getName(), p.getDescription(), p.getIndicator().getIndicatorType(), p.getIndicator().getId(), p.getPatient().getUsername(), p.getPatient().getName());
    }

    private List<BiomedicalIndicatorMeasureResponsePatientDTO> toDTOs(List<PatientBiomedicalIndicator> l) {
        return l.stream().map(this::toDTO).collect(Collectors.toList());
    }



}
