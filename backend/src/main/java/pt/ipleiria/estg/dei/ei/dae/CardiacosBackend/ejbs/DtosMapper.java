package pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.ejbs;

import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.ejb.Stateless;



public class DtosMapper<E, EM> {
    private ModelMapper mapper;
    private Class<EM> destinationClass;

    public DtosMapper(Class<EM> destination) {
        mapper = new ModelMapper();
        this.destinationClass = destination;
    }

    public EM getMappedEntity(E toMap) {
        return mapper.map(toMap, destinationClass);
    }

}
