package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws.AuthServices;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.Jwt.Jwt;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AuthDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AuthBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.JwtBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.logging.Logger;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
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
        if (auth != null && auth.startsWith("Bearer ")) {
            try {
                JWT j = JWTParser.parse(auth.substring(7));
                return Response.ok(j.getJWTClaimsSet().getClaims()).build();
                //Note: nimbusds converts token expiration time to milliseconds
            } catch (ParseException e) {
                log.warning(e.toString());
                return Response.status(400).build();
            }
        }
        return Response.status(204).build(); //no jwt means no claims to extract
    }
}