package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.DTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.util.Date;

public class DietResponseDTO extends TreatmentTypeResponseDTO {

    public DietResponseDTO() {
    }

    public DietResponseDTO(String name, String description, Date startDate, Date endDate,
                              HealthcareProfessional healthCareProfessional, PRC prc) {
        super(name, description, startDate, endDate, healthCareProfessional, prc);
    }

}
