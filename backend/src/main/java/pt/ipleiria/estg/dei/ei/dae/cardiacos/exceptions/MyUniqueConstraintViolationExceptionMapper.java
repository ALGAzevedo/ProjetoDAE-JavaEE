package pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.logging.Logger;

public class MyUniqueConstraintViolationExceptionMapper implements ExceptionMapper<MyConstraintViolationException> {
    private static final Logger logger = Logger.getLogger("exceptions.MyConstraintViolationExceptionMapper");

    @Override
    public Response toResponse(MyConstraintViolationException e) {

        String errorMsg = e.getMessage();
        logger.warning("ERROR: " + errorMsg);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorMsg).build();

    }
}
