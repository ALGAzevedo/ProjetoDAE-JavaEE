package pt.ipleiria.estg.dei.ei.dae.cardiacos.utils;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.DTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public <E, D extends DTO> D serialize(E entity, Class<D> clazz) {
        return modelMapper.map(entity, clazz);
    }

    public <E, D extends DTO> List<D> serialize(List<E> entities, Class<D> clazz) {
        return entities.stream().map(entity -> serialize(entity, clazz)).collect(Collectors.toList());
    }

    public <E, D extends DTO> E load(D dto, Class<E> clazz) {
        return modelMapper.map(dto, clazz);
    }

    public <E, D extends DTO> void hydrate(E entity, D dto) {
        modelMapper.map(dto, entity);
    }
}
