package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentType;

import javax.ejb.Stateless;

@Stateless
public class TreatmentTypeBean<E extends TreatmentType, PK extends Integer> extends BaseBean<E, Integer> {
    public TreatmentTypeBean() {
    }
}
