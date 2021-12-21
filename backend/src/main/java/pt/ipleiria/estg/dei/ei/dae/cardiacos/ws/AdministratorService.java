package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorCreateDto;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorResponseDto;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.AdministratorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.DtosMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("administrators") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class AdministratorService {
    @EJB
    private AdministratorBean administratorBean;


    @GET
    @Path("/")
    public List<AdministratorResponseDto> getAllAdministratorsWC() {

        return toDTOs(administratorBean.all());
    }

    @GET
    @Path("{username}")
    public Response getAdministratorDetails(@PathParam("username") String username) throws MyEntityNotFoundException {
        return Response.ok("estou aqui").build();
//        Administrator administrator = administratorBean.findOrFail(username);
        //return Response.ok(toDTO(administrator)).build();
    }

    @POST
    @Path("/")
    public Response createNewAdministrator(AdministratorCreateDto administratorCreateDTO) throws MyConstraintViolationException, MyEntityExistsException {
        DtosMapper<AdministratorCreateDto, Administrator> mapper = new DtosMapper<>(Administrator.class);
        Administrator administrator = administratorBean.create(mapper.getMappedEntity(administratorCreateDTO));
        return Response.status(Response.Status.CREATED).entity(toDTO(administrator)).build();
    }







    private AdministratorResponseDto toDTO(Administrator administrator) {
        DtosMapper<Administrator, AdministratorResponseDto> mapper = new DtosMapper<>(AdministratorResponseDto.class);
        return mapper.getMappedEntity(administrator);

    }

    // converts an entire list of entities into a list of DTOs
    private List<AdministratorResponseDto> toDTOs(List<Administrator> administrators) {
        return administrators.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
