package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.AdministratorResponseDto;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalResponseDto;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.DtosMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;

import java.util.List;
import java.util.stream.Collectors;

public class healthcareProfessionalsService {












    private HealthcareProfessionalResponseDto toDTO(HealthcareProfessional healthcareProfessional) {
        DtosMapper<HealthcareProfessional, HealthcareProfessionalResponseDto> mapper = new DtosMapper<>(HealthcareProfessionalResponseDto.class);
        return mapper.getMappedEntity(healthcareProfessional);

    }
    // converts an entire list of entities into a list of DTOs
    private List<HealthcareProfessionalResponseDto> toDTOs(List<HealthcareProfessional> healthcareProfessionals) {
        return healthcareProfessionals.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
