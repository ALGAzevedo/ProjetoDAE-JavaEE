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

    public ExerciseResponseDTO(Integer code, String name, String treatmentType, String description, LocalDate startDate, LocalDate endDate,
                               HealthcareProfessionalResponseDTO HealthcareProfessional, PRCResponseDTO prc) {
        super(code, name, treatmentType, description, startDate, endDate, HealthcareProfessional, prc);
    }
}
