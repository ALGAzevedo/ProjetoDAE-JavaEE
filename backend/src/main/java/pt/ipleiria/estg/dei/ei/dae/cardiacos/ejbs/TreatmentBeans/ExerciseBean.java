package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentTypeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Exercise;

import javax.ejb.Stateless;

@Stateless
public class ExerciseBean extends TreatmentTypeBean<Exercise, Integer> {
    public ExerciseBean() {
    }
}
