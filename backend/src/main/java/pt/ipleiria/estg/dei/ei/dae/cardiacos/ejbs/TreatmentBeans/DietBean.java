package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentTypeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Diet;

import javax.ejb.Stateless;

@Stateless
public class DietBean extends TreatmentTypeBean<Diet, Integer> {
    public DietBean() {
    }
}
