package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.time.LocalDate;
import java.util.Date;

public class EducationCreateDTO extends TreatmentTypeCreateDTO {

    public EducationCreateDTO() {
    }

    public EducationCreateDTO(String name, String description, LocalDate startDate, LocalDate endDate,
                              HealthcareProfessional HealthcareProfessional, PRC prc) {
        super(name, description, startDate, endDate, HealthcareProfessional, prc);
    }
}
