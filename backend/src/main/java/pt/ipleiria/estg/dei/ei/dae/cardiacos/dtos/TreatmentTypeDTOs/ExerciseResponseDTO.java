package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.HealthcareProfessionalResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PRCResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.time.LocalDate;
import java.util.Date;

public class ExerciseResponseDTO extends TreatmentTypeResponseDTO {

    public ExerciseResponseDTO() {
    }

    public ExerciseResponseDTO(String name, String description, LocalDate startDate, LocalDate endDate,
                               HealthcareProfessionalResponseDTO HealthcareProfessional, PRCResponseDTO prc) {
        super(name, description, startDate, endDate, HealthcareProfessional, prc);
    }
}
