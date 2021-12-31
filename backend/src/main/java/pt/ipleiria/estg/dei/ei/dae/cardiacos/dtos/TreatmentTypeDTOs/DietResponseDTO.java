package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.DTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PRCResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.time.LocalDate;
import java.util.Date;

public class DietResponseDTO extends TreatmentTypeResponseDTO {

    public DietResponseDTO() {
    }

    public DietResponseDTO(Integer code, String name, String description, LocalDate startDate, LocalDate endDate,
                           HealthcareProfessionalResponseDTO HealthcareProfessional, PRCResponseDTO prc) {
        super(code, name, description, startDate, endDate, HealthcareProfessional, prc);
    }

}
