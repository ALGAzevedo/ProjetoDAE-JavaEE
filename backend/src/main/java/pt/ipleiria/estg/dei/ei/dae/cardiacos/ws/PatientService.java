package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorMeasureResponsePatientDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PatientBiomedicalIndicator;
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
        System.out.println(dto.getDate());
        patientBean.addQuantitativeBiomedicalIndicator(username, dto);
        return Response.ok(dto).build();

    }

    @GET
    @Path("{username}/biomedicalRegisters")
    public Response getBiomedicalRegisters(@PathParam("username") String username ) throws MyEntityNotFoundException {
        List<PatientBiomedicalIndicator> pi = patientBean.getPatientRegisters(username);



        //return Response.ok(pi.get(0).getDate()).build();
        return Response.ok(toDTOs(pi)).build();
        

    }

    @GET
    @Path("{username}/biomedicalRegisters/{id}")
    public Response getBiomedicalRegister(@PathParam("username") String username, @PathParam("id") Long id ) throws MyEntityNotFoundException {

        return Response.ok(toDTO(patientBean.getPatientRegister(username, id))).build();


    }

    @DELETE
    @Path("{username}/biomedicalRegisters/{id}")
    public Response PostQuantitativeMeasure(@PathParam("username") String username, @PathParam("id") Long id ) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        patientBean.removePatientRegisters(username, id);
        return Response.noContent().build();

    }
    @PUT
    @Path("{username}/biomedicalRegisters/{id}")
    public Response PutQuantitativeMeasure(@PathParam("username") String username, @PathParam("id") Long id ) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException, MyConstraintViolationException {

        return Response.ok(toDTO(patientBean.editPatientRegisters(username, id))).build();

    }


    private BiomedicalIndicatorMeasureResponsePatientDTO toDTO(PatientBiomedicalIndicator p) {
        if(p == null)
            return null;

        return new BiomedicalIndicatorMeasureResponsePatientDTO(p.getId(), p.getDate(),
                p.getValue(), p.getIndicator().getName(), p.getDescription(), p.getIndicator().getIndicatorType(), p.getIndicator().getId());
    }

    private List<BiomedicalIndicatorMeasureResponsePatientDTO> toDTOs(List<PatientBiomedicalIndicator> l) {
        return l.stream().map(this::toDTO).collect(Collectors.toList());
    }





}
