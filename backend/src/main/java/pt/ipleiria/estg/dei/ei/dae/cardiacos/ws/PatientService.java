package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorGeneralResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyUniqueConstraintViolationException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("patients") // relative url web path for this service
public class PatientService extends BaseService<Patient, String, PatientBean, PatientCreateDTO, PatientResponseDTO> {
    @EJB
    private PatientBean patientBean;

    @Override
    protected PatientBean getEntityBean() {
        return patientBean;
    }


    //add indicators to its register
    @POST
    @Path("{username}/biomedicalRegisters/qualitative")
    public Response PostQualitativeMeasure(@PathParam("username") String username, QualitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        patientBean.addQualitativeBiomedicalIndicator(username, dto);
        return Response.ok(dto).build();
    }

    @POST
    @Path("{username}/biomedicalRegisters/quantitative")
    public Response PostQuantitativeMeasure(@PathParam("username") String username, QuantitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        patientBean.addQuantitativeBiomedicalIndicator(username, dto);
        return Response.ok(dto).build();
    }

    @GET
    @Path("{username}/prcs")
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


}
