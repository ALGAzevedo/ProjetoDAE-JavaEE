package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentTypeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Behaviour;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Stateless
public class BehaviourBean extends TreatmentTypeBean<Behaviour, Integer> {
    public BehaviourBean() {
    }

    public List<Behaviour> getBehaviours(MultivaluedMap<String, String> queryParams) throws MyEntityNotFoundException {
        return getTreatments(queryParams);
    }


}
