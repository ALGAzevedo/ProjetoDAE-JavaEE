package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;


import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorQualitativeCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.DTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BaseEntity;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.utils.EntityMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.utils.TypeResolver;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public abstract class BaseService<E extends BaseEntity, PK, B extends BaseBean<E, PK>, D extends DTO, DR extends DTO> {

    private Class<D> dtoCreateClass = TypeResolver.getGenericClazz(this, 3);
    private Class<DR> dtoResponseClass = TypeResolver.getGenericClazz(this, 4);
    private ModelMapper modelMapper = new ModelMapper();

    public Class<D> getDtoCreateClass() {
        return dtoCreateClass;
    }
    public Class<DR> getDtoResponseClass() {
        return dtoResponseClass;
    }

    @Inject
    private EntityMapper mapper;

    protected abstract B getEntityBean();

    @GET
    @Path("/")
    public Response all() {
        var entities = getEntityBean().all();
        var dtos = mapper.serialize(entities, getDtoResponseClass());

        return Response.ok(dtos).build();
    }

    @POST
    @Path("/")
    public Response create(D dto) throws MyConstraintViolationException, MyEntityNotFoundException, MyEntityExistsException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        var entity = mapper.load(dto, getEntityBean().getEntityClass());
        getEntityBean().create(entity);

        return Response.ok(mapper.serialize(entity, getDtoResponseClass())).build();
    }

    @GET
    @Path("{pk}")
    public Response retrieve(@PathParam("pk") PK primaryKey) throws MyEntityNotFoundException {
        var entity = getEntityBean().findOrFail(primaryKey);
        return Response.ok(mapper.serialize(entity, getDtoResponseClass())).build();
    }

    protected void preUpdate(E entity) {

    }

    @PUT
    @Path("{pk}")
    public Response update(@PathParam("pk") PK primaryKey, D dto) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        var entity = getEntityBean().findOrFail(primaryKey);

        preUpdate(entity);

        mapper.hydrate(entity, dto);

        getEntityBean().update(entity);

        return Response.ok(mapper.serialize(entity, getDtoResponseClass())).build();
    }

    @DELETE
    @Path("{pk}")
    public Response delete(@PathParam("pk") PK primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        getEntityBean().destroy(primaryKey);
        return Response.noContent().build();
    }
}
