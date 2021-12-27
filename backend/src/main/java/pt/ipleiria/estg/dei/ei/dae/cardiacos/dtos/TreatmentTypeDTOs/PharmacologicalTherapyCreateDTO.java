package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeDTOs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.TreatmentTypeCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;

import java.util.Date;

public class PharmacologicalTherapyCreateDTO extends TreatmentTypeCreateDTO {

    public PharmacologicalTherapyCreateDTO() {
    }

    public PharmacologicalTherapyCreateDTO(String name, String description, Date startDate, Date endDate,
                                           HealthcareProfessional healthCareProfessional, PRC prc) {
        super(name, description, startDate, endDate, healthCareProfessional, prc);
    }
}