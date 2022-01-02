package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.AuthServices;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.Jwt.Jwt;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AuthDTO;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.UserMeDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AdministratorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AuthBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.JwtBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.logging.Logger;


@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Path("/auth")
public class LoginService {
    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());
    @EJB
    JwtBean jwtBean;
    @EJB
    AuthBean authBean;
    @POST
    @Path("/login")
    public Response authenticateUser(AuthDTO authDTO) {
       //FIXME: Retirar o try..catch para ver todas as exceptions ou deixar apenas o UNAUTHORIZED
        try {
            Auth auth = authBean.authenticate(authDTO.getUsername(), authDTO.getPassword());
            if (auth != null) {

                if (auth.getUsername() != null) {
                    log.info("Generating JWT for user " + auth.getUsername());
                }
                String token = jwtBean.createJwt(auth.getUsername(), new
                        String[]{auth.getClass().getSimpleName()});
                return Response.ok(new Jwt(token)).build();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect username and password").build();
    }


    @GET
    @Path("/user")
    public Response demonstrateClaims(@HeaderParam("Authorization") String auth) {
        ModelMapper mapper = new ModelMapper();


        if (auth != null && auth.startsWith("Bearer ")) {
            try {
                JWT j = JWTParser.parse(auth.substring(7));
                String subject = j.getJWTClaimsSet().getSubject();
                //we return a dto with all informations of user
                User usr = authBean.userMe(subject);

                UserMeDTO dto = mapper.map(usr, UserMeDTO.class);
                dto.setUserType(usr.getClass().getSimpleName());

                return Response.ok(dto).build();

                //return Response.ok(j.getJWTClaimsSet().getClaims()).build();
                //Note: nimbusds converts token expiration time to milliseconds
            } catch (ParseException | MyEntityNotFoundException e) {
                log.warning(e.toString());
                return Response.status(400).build();
            }
        }
        return Response.status(204).build(); //no jwt means no claims to extract


    }


}