package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.DTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PRCResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.time.LocalDate;
import java.util.Date;

public class BehaviourResponseDTO extends TreatmentTypeResponseDTO {

    public BehaviourResponseDTO() {
    }

    public BehaviourResponseDTO(Integer code, String name, String treatmentType, String description, LocalDate startDate, LocalDate endDate,
                                HealthcareProfessionalResponseDTO HealthcareProfessional, PRCResponseDTO prc) {
        super(code, name, treatmentType, description, startDate, endDate, HealthcareProfessional, prc);
    }
}
