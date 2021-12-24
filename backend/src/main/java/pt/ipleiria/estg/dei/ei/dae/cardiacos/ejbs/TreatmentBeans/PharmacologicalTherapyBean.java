package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentTypeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.PharmacologicalTherapy;

import javax.ejb.Stateless;

@Stateless
public class PharmacologicalTherapyBean extends TreatmentTypeBean<PharmacologicalTherapy, Integer> {
    public PharmacologicalTherapyBean() {
    }
}
