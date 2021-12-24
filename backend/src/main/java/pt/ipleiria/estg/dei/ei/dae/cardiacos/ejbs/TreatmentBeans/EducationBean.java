package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentTypeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Education;

import javax.ejb.Stateless;

@Stateless
public class EducationBean extends TreatmentTypeBean<Education, Integer> {
    public EducationBean() {
    }
}
