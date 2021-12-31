package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.time.LocalDate;
import java.util.Date;

public class DietCreateDTO extends TreatmentTypeCreateDTO {

    public DietCreateDTO() {
    }

    public DietCreateDTO(String name, String description, LocalDate startDate, LocalDate endDate,
                         String healthcareProfessionalUsername, String prcCode) {
        super(name, description, startDate, endDate, healthcareProfessionalUsername, prcCode);
    }
}
