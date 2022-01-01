package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.AuthServices;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PasswordCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AuthBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundExceptionMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.logging.Logger;
//{pass: ..., confirmPass: ..., token: ...}
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class ConfirmService {

    private static final Logger log =
            Logger.getLogger(RegistrationService.class.getName());

    private final static String USER_CREATED_MSG =
            "New user with email %s created";
    @EJB
    private PatientBean patientBean;
    @EJB
    private AuthBean authBean;

    @POST
    @Path("/confirm/{token}")
    public Response updateUserPassword(@PathParam("token") String token, PasswordCreateDTO passDTO) throws Exception {

        authBean.confirmToken(token, passDTO);

        return Response.status(Response.Status.OK).entity("Patient password updated!").build();

//        Auth auth = authBean.findByToken(token);
//        if (auth != null && !auth.isExpired()){
//
//            if (!passDTO.getPassword().equals(passDTO.getConfirmPassword())) {
//                return Response.status(Response.Status.BAD_REQUEST).entity("Password and confirm password do not match").build();
//            }
//            //TODO: Update password on Patient table  or patient password in Auth table  only
//            try {
//                auth.setPassword(passDTO.getPassword());
//                auth.setActive(true);
//                auth.setExpired(true);
//                authBean.update(auth);
//            }catch (Exception e){
//                return Response.status(400).build();
//            }
//
//            return Response.status(Response.Status.OK).entity("Patient password updated!").build();
//        }

        //throw new MyEntityNotFoundException("Invalid token code");

    }
}
